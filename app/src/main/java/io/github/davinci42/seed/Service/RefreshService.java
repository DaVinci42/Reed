package io.github.davinci42.seed.Service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.security.PrivilegedAction;
import java.util.List;

import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;
import io.github.davinci42.seed.Model.Utils.SeedCallback;

/**
 * Created by davinci42 on 15/11/16.
 */
public class RefreshService extends IntentService {

    private static final String TAG = "RefreshService";
    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();
    private SQLiteDatabase mDatabase;

    public RefreshService() {
        super(TAG);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RefreshService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mDatabase = new FeedDbHelper(getApplicationContext()).getWritableDatabase();

        if (SignHelper.ifIdAndTokenReady()) {
            updateFeedlyData();
        }
    }

    private void updateFeedlyData() {
        getSubscriptionList();
        getUnreadEntryList();
        getRecentlyReadEntryList();
        getSavedForLaterEntryList();
    }

    private void getSubscriptionList() {

        mFeedNetwork.getSubscriptionList(new SeedCallback<Subscription>() {
            @Override
            public void onSuccess(List<Subscription> feedlyDataList) {
                updateFeedDb(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    private void getUnreadEntryList() {

        mFeedNetwork.getUnreadEntryList(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                updateUnreadEntryDb(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }



    private void getRecentlyReadEntryList() {
        mFeedNetwork.getRecentlyRead(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                updateRecentlyReadEntryDb(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }


    private void getSavedForLaterEntryList() {
        mFeedNetwork.getSavedForLaterEntryList(new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                updateSavedForLaterEntryDb(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    private void updateUnreadEntryDb(List<Entry> entryList) {

    }


    private void updateRecentlyReadEntryDb(List<Entry> entryList) {}


    private void updateSavedForLaterEntryDb(List<Entry> entryList) {

    }

    private void updateFeedDb(List<Subscription> feedlyDataList) {

        mDatabase.delete(FeedDbSchema.FeedTable.NAME, null, null);

        for (Subscription subs : feedlyDataList) {
            ContentValues values = new ContentValues();
            values.put(FeedDbSchema.Cols.ID, subs.id);
            values.put(FeedDbSchema.Cols.TITLE, subs.title);

            String categoryLabel = "";
            String categoryId = "";
            if (subs.categories != null && !subs.categories.isEmpty()) {
                for (int i = 0; i < subs.categories.size(); i++) {
                    categoryLabel = categoryLabel + subs.categories.get(i).label + " ";
                    categoryId = categoryId + subs.categories.get(i).id + " ";
                }
            }
            values.put(FeedDbSchema.Cols.CATEGORYId, categoryId);
            values.put(FeedDbSchema.Cols.CATEGORYLabel, categoryLabel);

            String iconUrl = "";
            if (subs.iconUrl != null && !subs.iconUrl.isEmpty()) {
                iconUrl = subs.iconUrl;
            }
            values.put(FeedDbSchema.Cols.ICONURL, iconUrl);
            mDatabase.insert(FeedDbSchema.FeedTable.NAME, null, values);
            Log.e("davinci42", "Database Update");
        }

    }
}
