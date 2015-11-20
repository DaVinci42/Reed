package io.github.davinci42.seed.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import io.github.davinci42.seed.Database.RecentlyEntryDbHelper;
import io.github.davinci42.seed.Database.SavedEntryDbHelper;
import io.github.davinci42.seed.Database.UnreadEntryDbHelper;
import io.github.davinci42.seed.Database.EntryDbSchema;
import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Model.Entity.Category;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.Model.Utils.SeedCallback;
import io.github.davinci42.seed.MvpBase.MvpPresenter;
import io.github.davinci42.seed.View.ViewInterface.MainView;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter extends MvpPresenter<MainView> {

    public Context mContext;

    public void getContext(Context context) {
        mContext = context;
    }

    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

    public void updateFeedDb() {

        mFeedNetwork.getSubscriptionList(new SeedCallback<Subscription>() {
            @Override
            public void onSuccess(List<Subscription> feedlyDataList) {
                updateFeedDbFromSubs(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    public void updateUnreadEntryDb() {
        mFeedNetwork.getUnreadEntryList(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                Log.e("davinci42", "Unread: " + feedlyDataList.size());
                updateUnreadDbFromEntryList(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    private void updateUnreadDbFromEntryList(List<Entry> entryList) {
        UnreadEntryDbHelper unreadEntryDbHelper = new UnreadEntryDbHelper(mContext);
        SQLiteDatabase db = unreadEntryDbHelper.getWritableDatabase();
        unreadEntryDbHelper.onUpgrade(db, 1, 1);

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

            db.insert(EntryDbSchema.EntryTable.NAME, null, values);

        }
    }

    public void updateRecentlyEntryDb() {
        mFeedNetwork.getRecentlyEntryList(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                Log.e("davinci42", "Recently: " + feedlyDataList.size());
                updateRecentlyDbFromEntryList(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    private void updateRecentlyDbFromEntryList(List<Entry> entryList) {
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

            db.insert(EntryDbSchema.EntryTable.NAME, null, values);

        }

    }

    public void updateSavedEntryDb() {
        mFeedNetwork.getSavedForLaterEntryList(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                Log.e("davinci42", "Saved: " + feedlyDataList.size());
                updateSavedDbFromEntryList(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    private void updateSavedDbFromEntryList(List<Entry> entryList) {
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

            db.insert(EntryDbSchema.EntryTable.NAME, null, values);

        }
    }

    private void updateFeedDbFromSubs(List<Subscription> subscriptionList) {
        FeedDbHelper feedDbHelper = new FeedDbHelper(mContext);
        SQLiteDatabase db = feedDbHelper.getWritableDatabase();
        feedDbHelper.onUpgrade(db, 1, 1);

        for (Subscription subs : subscriptionList) {
            ContentValues values = new ContentValues();

            values.put(FeedDbSchema.Cols.ID, subs.id);
            values.put(FeedDbSchema.Cols.TITLE, subs.title);

            String categoryId = "";
            String categoryLabel = "";
            if (!subs.categories.isEmpty()) {
                for (Category category : subs.categories) {

                    // Just remind to handle ';' at String end
                    categoryId = categoryId + category.id + ";";
                    categoryLabel = categoryLabel + category.label + ";";

                }
            }

            values.put(FeedDbSchema.Cols.CATEGORYId, categoryId);
            values.put(FeedDbSchema.Cols.CATEGORYLabel, categoryLabel);
            values.put(FeedDbSchema.Cols.ICONURL, subs.iconUrl);
            db.insert(FeedDbSchema.FeedTable.NAME, null, values);
        }
    }

}
