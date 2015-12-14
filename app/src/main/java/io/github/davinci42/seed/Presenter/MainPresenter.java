package io.github.davinci42.seed.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import io.github.davinci42.seed.Database.EntryDbSchema;
import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Database.RecentlyEntryDbHelper;
import io.github.davinci42.seed.Database.SavedEntryDbHelper;
import io.github.davinci42.seed.Database.UnreadEntryDbHelper;
import io.github.davinci42.seed.Model.Entity.Category;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.Model.Utils.SeedCallback;
import io.github.davinci42.seed.MvpBase.BasePresenter;
import io.github.davinci42.seed.View.ViewInterface.MainView;
import java.util.List;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter extends BasePresenter<MainView> {

	public Context mContext;

	public void getContext(Context context) {
		mContext = context;
	}

	private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

	public void updateFeedDb() {

		mFeedNetwork.getSubscriptionList(new SeedCallback<Subscription>() {
			@Override public void onSuccess(List<Subscription> feedlyDataList) {
				updateFeedDbFromSubs(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	public void updateUnreadEntryDb() {
		mFeedNetwork.getUnreadEntryList(new SeedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				updateUnreadDbFromEntryList(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	private synchronized void updateUnreadDbFromEntryList(List<Entry> entryList) {

		UnreadEntryDbHelper unreadEntryDbHelper = new UnreadEntryDbHelper(mContext);
		SQLiteDatabase entryDb = unreadEntryDbHelper.getWritableDatabase();
		unreadEntryDbHelper.onUpgrade(entryDb, 1, 1);

		for (Entry entry : entryList) {
			ContentValues values = new ContentValues();

			values.put(EntryDbSchema.Cols.ID, entry.id);
			values.put(EntryDbSchema.Cols.TITLE, entry.title);
			values.put(EntryDbSchema.Cols.AUTHOR, entry.author);
			values.put(EntryDbSchema.Cols.UPDATED, entry.published);

			if (entry.origin != null) {
				values.put(EntryDbSchema.Cols.FEEDID, entry.origin.streamId);
				values.put(EntryDbSchema.Cols.FEEDTITLE, entry.origin.title);
			}

			if (entry.content != null) {
				values.put(EntryDbSchema.Cols.CONTENT, entry.content.content);
			}

			entryDb.insert(EntryDbSchema.EntryTable.UNREAD_TABLE_NAME, null, values);
		}

		getView().onUnreadDbUpdated();
	}

	public void updateRecentlyEntryDb() {
		mFeedNetwork.getRecentlyEntryList(new SeedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				updateRecentlyDbFromEntryList(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	private synchronized void updateRecentlyDbFromEntryList(List<Entry> entryList) {
		RecentlyEntryDbHelper recentlyEntryDbHelper = new RecentlyEntryDbHelper(mContext);
		SQLiteDatabase db = recentlyEntryDbHelper.getWritableDatabase();
		recentlyEntryDbHelper.onUpgrade(db, 1, 1);

		for (Entry entry : entryList) {
			ContentValues values = new ContentValues();

			values.put(EntryDbSchema.Cols.ID, entry.id);
			values.put(EntryDbSchema.Cols.TITLE, entry.title);
			values.put(EntryDbSchema.Cols.AUTHOR, entry.author);
			values.put(EntryDbSchema.Cols.UPDATED, entry.published);
			values.put(EntryDbSchema.Cols.FEEDID, entry.origin.streamId);

			if (entry.content != null) {
				values.put(EntryDbSchema.Cols.CONTENT, entry.content.content);
			}

			db.insert(EntryDbSchema.EntryTable.RECENTLY_TABLE_NAME, null, values);
		}
		getView().onRecentlyDbUpdated();
	}

	public void updateSavedEntryDb() {
		mFeedNetwork.getSavedForLaterEntryList(new SeedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				updateSavedDbFromEntryList(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	private synchronized void updateSavedDbFromEntryList(List<Entry> entryList) {
		SavedEntryDbHelper savedEntryDbHelper = new SavedEntryDbHelper(mContext);
		SQLiteDatabase db = savedEntryDbHelper.getWritableDatabase();
		savedEntryDbHelper.onUpgrade(db, 1, 1);

		for (Entry entry : entryList) {
			ContentValues values = new ContentValues();

			values.put(EntryDbSchema.Cols.ID, entry.id);
			values.put(EntryDbSchema.Cols.TITLE, entry.title);
			values.put(EntryDbSchema.Cols.AUTHOR, entry.author);
			values.put(EntryDbSchema.Cols.UPDATED, entry.published);
			values.put(EntryDbSchema.Cols.FEEDID, entry.origin.streamId);

			if (entry.content != null) {
				values.put(EntryDbSchema.Cols.CONTENT, entry.content.content);
			}

			db.insert(EntryDbSchema.EntryTable.SAVED_TABLE_NAME, null, values);
		}

		getView().onSavedDbUpdated();
	}

	private synchronized void updateFeedDbFromSubs(List<Subscription> subscriptionList) {
		FeedDbHelper feedDbHelper = new FeedDbHelper(mContext);
		SQLiteDatabase db = feedDbHelper.getWritableDatabase();
		feedDbHelper.onUpgrade(db, 1, 1);

		for (Subscription subs : subscriptionList) {
			ContentValues values = new ContentValues();

			values.put(FeedDbSchema.Cols.FEEDID, subs.id);
			values.put(FeedDbSchema.Cols.FEEDTITLE, subs.title);

			String categoryId = "";
			String categoryLabel = "";
			if (!subs.categories.isEmpty()) {
				for (Category category : subs.categories) {

					// Just remind to handle '; ' at String end
					categoryId = categoryId + category.id + "; ";
					categoryLabel = categoryLabel + category.label + "; ";
				}
			}
			values.put(FeedDbSchema.Cols.CATEGORYId, categoryId);
			values.put(FeedDbSchema.Cols.CATEGORYLABEL, categoryLabel);
			values.put(FeedDbSchema.Cols.ICONURL, subs.iconUrl);
			db.insert(FeedDbSchema.FeedTable.NAME, null, values);
		}

		getView().onFeedDbUpdated();
	}
}
