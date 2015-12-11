package io.github.davinci42.seed.Presenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import io.github.davinci42.seed.Database.EntryDbSchema;
import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Database.UnreadEntryDbHelper;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BasePresenter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 15/11/4.
 */
public class TabListPresenter extends BasePresenter<TabListView> {

	// feed id, category label
	private Map<String, String> mCategoryMap = new HashMap<>();
	private Context mContext;

	public void getCategoryList() {
		FeedDbHelper feedDbHelper = new FeedDbHelper(mContext);
		SQLiteDatabase feedDb = feedDbHelper.getReadableDatabase();

		String[] projection = {
			FeedDbSchema.Cols.FEEDID, FeedDbSchema.Cols.CATEGORYLabel,
		};

		Cursor cursor = feedDb.query(FeedDbSchema.FeedTable.NAME, projection, null, null, null, null, null);
		int count = cursor.getColumnCount();
		if (count != 0) {
			for (int i = 0; i < count; i++) {
				cursor.moveToPosition(i);
				String feedId = cursor.getString(cursor.getColumnIndexOrThrow(FeedDbSchema.Cols.FEEDID));
				String categoryLabel = cursor.getString(cursor.getColumnIndexOrThrow(FeedDbSchema.Cols.CATEGORYLabel));
				mCategoryMap.put(feedId, categoryLabel);
			}
		}
		cursor.close();
	}

	public void updateUnreadList(Context context) {
		mContext = context;
		if (mCategoryMap.isEmpty()) {
			getCategoryList();
		}
		UnreadEntryDbHelper dbHelper = new UnreadEntryDbHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		List<Entry> unreadList = new ArrayList<>();

		String[] projection = {
			EntryDbSchema.Cols.TITLE, EntryDbSchema.Cols.AUTHOR, EntryDbSchema.Cols.CONTENT, EntryDbSchema.Cols.UPDATED,
		};

		String sortOrder = EntryDbSchema.Cols.UPDATED + " DESC";

		Cursor cursor =
			db.query(EntryDbSchema.EntryTable.UNREAD_TABLE_NAME, projection, null, null, null, null, sortOrder);

		int count = cursor.getColumnCount();
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
				entry.categoryList.add(mCategoryMap.get(entry.origin.streamId));
				unreadList.add(entry);
			}
		}

		cursor.close();

		getView().updateEsData(unreadList);
	}

	public void updateRecentlyList() {

	}

	public void updateSavedList() {

	}
}
