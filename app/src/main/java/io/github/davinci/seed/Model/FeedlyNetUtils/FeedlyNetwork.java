package io.github.davinci.seed.Model.FeedlyNetUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.github.davinci.seed.Model.Entity.Category;
import io.github.davinci.seed.Model.Entity.Entry;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.FeedlyData;
import io.github.davinci.seed.Model.Entity.Subscription;
import io.github.davinci.seed.Model.Entity.UnreadCount;
import io.github.davinci.seed.Model.Entity.UnreadcountsEntity;
import io.github.davinci.seed.Model.Utils.NetUtils;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.Model.Utils.SeedNetCallback;

/**
 * Created by davinci42 on 15/10/21.
 */
public class FeedlyNetwork {

    NetUtils mNetUtils = new NetUtils();

    String rootUrl = "http://cloud.feedly.com";

    String userId = SignHelper.getUserId();

    public void updateCategoryFeedMap(final SeedCallback<Subscription> seedCallback) {

        final String href = rootUrl + "/v3/subscriptions";
        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {

                List<Subscription> subscriptionList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Subscription>>() {
                }.getType());
                seedCallback.onSuccess(subscriptionList);
            }

            @Override
            public void onException(Exception e) {
                seedCallback.onException(e);
            }
        });
    }


    public void getCategoryList(final SeedCallback<Category> seedCallback) {

        String href = rootUrl + "/v3/categories";

        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String response) {
                List<Category> categoryList = new Gson().fromJson(response, new TypeToken<ArrayList<Category>>() {
                }.getType());
                seedCallback.onSuccess(categoryList);
            }

            @Override
            public void onException(Exception e) {
                seedCallback.onException(e);
            }
        });
    }

    public void getRecentlyRead(final SeedCallback seedCallback) {

        String href = rootUrl + "user" + userId + "/tag/global.read";
        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                List<Entry> entryList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Entry>>() {
                }.getType());
                seedCallback.onSuccess(entryList);
            }

            @Override
            public void onException(Exception e) {
                seedCallback.onException(e);
            }
        });
    }

    public void getSavedForLaterEntryList(final SeedCallback<Entry> seedCallback) {

        String href = rootUrl + "user/" + userId + "/tag/global.saved";
        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                List<String> entryIdList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<String>>() {
                }.getType());
                getEntryListWithId(entryIdList, new SeedCallback<Entry>() {
                    @Override
                    public void onSuccess(List<Entry> feedlyDataList) {
                        seedCallback.onSuccess(feedlyDataList);
                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    public void getEntryListWithId(List<String> entryIdList, final SeedCallback<Entry> seedCallback) {

        String postData = new Gson().toJson(entryIdList);

        String href = rootUrl + "/v3/entries/.mget";

        mNetUtils.doPost(href, postData, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                List<Entry> entryList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Entry>>() {
                }.getType());
                seedCallback.onSuccess(entryList);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    public void getUnreadFeed(final SeedCallback<UnreadcountsEntity> seedCallback) {

        String href = rootUrl + "/v3/markers/counts";

        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                UnreadCount unreadCount = new Gson().fromJson(httpResponse, UnreadCount.class);
                seedCallback.onSuccess(unreadCount.unreadcounts);
            }

            @Override
            public void onException(Exception e) {

            }
        });

    }

    public void getFeedList(List<String> feedIdList, final SeedCallback<Feed> seedCallback) {

        String href = rootUrl + "/v3/feeds/.mget";
        String postData = new Gson().toJson(feedIdList);

        mNetUtils.doPost(href, postData, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                List<Feed> feedList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Feed>>() {
                }.getType());
                seedCallback.onSuccess(feedList);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }


}
