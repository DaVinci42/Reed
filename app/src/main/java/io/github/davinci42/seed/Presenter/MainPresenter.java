package io.github.davinci42.seed.Presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import io.github.davinci42.seed.Database.FeedDbHelper;
import io.github.davinci42.seed.Database.FeedDbSchema;
import io.github.davinci42.seed.Model.Entity.Category;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.Model.Utils.SeedCallback;
import io.github.davinci42.seed.MvpBase.MvpPresenter;
import io.github.davinci42.seed.View.ViewInterface.MainView;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter extends MvpPresenter<MainView>{

    public Context mContext;

    public void getContext(Context context) {
        mContext = context;
    }

    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

    public void updateFeedDb(){

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


    public void updateUnreadEntryDb() {}


    public void updateRecentlyEntryDb() {}


    public void updateSavedEntryDb() {}

    private void updateFeedDbFromSubs(List<Subscription> subscriptionList) {
        FeedDbHelper feedDbHelper = new FeedDbHelper(mContext);
        SQLiteDatabase db = feedDbHelper.getWritableDatabase();

        feedDbHelper.onCreate(db);

        for (Subscription subs : subscriptionList) {
            ContentValues values = new ContentValues();

            String categoryId = "";
            String categoryLabel = "";
            if (!subs.categories.isEmpty()) {
                for (Category category : subs.categories) {

                    // Just remind to handle ';' at String end
                    categoryId = categoryId + category.id + ";";
                    categoryLabel = categoryLabel + category.label + ";";

                }
            }
            values.put(FeedDbSchema.Cols.ID, subs.id);
            values.put(FeedDbSchema.Cols.TITLE, subs.title);
            values.put(FeedDbSchema.Cols.CATEGORYId, categoryId);
            values.put(FeedDbSchema.Cols.CATEGORYLabel, categoryLabel);
            values.put(FeedDbSchema.Cols.ICONURL, subs.iconUrl);
            db.insert(FeedDbSchema.FeedTable.NAME, null, values);
        }


    }

}
