package io.github.davinci42.seed.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import io.github.davinci42.seed.Database.EntryDbSchema;
import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Database.RecentlyEntryDbHelper;
import io.github.davinci42.seed.Database.SavedEntryDbHelper;
import io.github.davinci42.seed.Database.UnreadEntryDbHelper;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BasePresenter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 15/11/4.
 */
public class TabListPresenter extends BasePresenter<TabListView> {

	// feed id, category label
	private Map<String, List<String>> mCategoryMap = new HashMap<>();
	private Context mContext;

	public synchronized void getCategoryList() {
		FeedDbHelper dbHelper = new FeedDbHelper(mContext);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		mCategoryMap.clear();

		String[] projection = {
			FeedDbSchema.Cols.FEEDID, FeedDbSchema.Cols.CATEGORYLABEL,
		};

		Cursor cursor = db.query(FeedDbSchema.FeedTable.NAME, projection, null, null, null, null, null);
		int count = cursor.getCount();
		if (count != 0) {
			for (int i = 0; i < count; i++) {
				cursor.moveToPosition(i);
				String feedId = cursor.getString(cursor.getColumnIndexOrThrow(FeedDbSchema.Cols.FEEDID));
				String categoryLabel = cursor.getString(cursor.getColumnIndexOrThrow(FeedDbSchema.Cols.CATEGORYLABEL));
				String[] category = categoryLabel.split("; ");
				mCategoryMap.put(feedId, Arrays.asList(category));
			}
		}
		cursor.close();
		db.close();
	}

	public synchronized void updateEntryList(Context context, SQLiteOpenHelper openHelper) {
		mContext = context;
		if (mCategoryMap.isEmpty()) {
			getCategoryList();
		}

		SQLiteDatabase db = openHelper.getReadableDatabase();
		openHelper.onCreate(db);
		List<Entry> entryList = new ArrayList<>();

		String tableName;

		if (openHelper instanceof UnreadEntryDbHelper) {
			tableName = EntryDbSchema.EntryTable.UNREAD_TABLE_NAME;
		} else if (openHelper instanceof RecentlyEntryDbHelper) {
			tableName = EntryDbSchema.EntryTable.RECENTLY_TABLE_NAME;
		} else if (openHelper instanceof SavedEntryDbHelper) {
			tableName = EntryDbSchema.EntryTable.SAVED_TABLE_NAME;
		} else {
			tableName = "";
		}

		String[] projection = {
			EntryDbSchema.Cols.TITLE, EntryDbSchema.Cols.AUTHOR, EntryDbSchema.Cols.CONTENT, EntryDbSchema.Cols.UPDATED,
			EntryDbSchema.Cols.FEEDTITLE, EntryDbSchema.Cols.FEEDID
		};

		String sortOrder = EntryDbSchema.Cols.UPDATED + " DESC";

		Cursor cursor = db.query(tableName, projection, null, null, null, null, sortOrder);

		int count = cursor.getCount();
		if (count != 0) {
			for (int i = 0; i < count; i++) {
				cursor.moveToPosition(i);
				Entry entry = new Entry();
				entry.title = cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.TITLE));
				entry.author = cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.AUTHOR));
				entry.content.content = cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.CONTENT));
				entry.origin.title = cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.FEEDTITLE));
				entry.origin.streamId = cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.FEEDID));
				entry.published =
					Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(EntryDbSchema.Cols.UPDATED)));

				if (mCategoryMap.get(entry.origin.streamId) != null) {
					entry.categoryList = mCategoryMap.get(entry.origin.streamId);
				}
				entryList.add(entry);
			}
		}

		cursor.close();
		db.close();

		getView().updateElvData(entryList);
	}
}
