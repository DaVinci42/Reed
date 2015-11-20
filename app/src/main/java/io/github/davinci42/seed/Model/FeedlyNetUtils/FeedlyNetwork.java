package io.github.davinci42.seed.Model.FeedlyNetUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.Feed;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.StreamEntryList;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.Utils.NetUtils;
import io.github.davinci42.seed.Model.Utils.SeedCallback;
import io.github.davinci42.seed.Model.Utils.SeedNetCallback;

/**
 * Created by davinci42 on 15/10/21.
 */
public class FeedlyNetwork {

    NetUtils mNetUtils = new NetUtils();

    String rootUrl = "http://cloud.feedly.com";

    String userId = SignHelper.getUserId();

    public void getSubscriptionList(final SeedCallback<Subscription> seedCallback) {

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
                seedCallback.onException(String.valueOf(e));
            }
        });
    }


    public void getUnreadEntryList(final SeedCallback<Entry> seedCallback) {
        String streamId = FeedlyData.ALL_CATEGORY_ID;
        int count = 1000;

        getStreamEntryListWithId(true, streamId, count, new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                seedCallback.onSuccess(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    public void getRecentlyEntryList(final SeedCallback<Entry> seedCallback) {

        String streamId = FeedlyData.RECENTLY_READ;
        int count = 200;

        getStreamEntryListWithId(false, streamId, count, new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                seedCallback.onSuccess(feedlyDataList);
            }

            @Override
            public void onException(String error) {

            }
        });
    }

    public void getSavedForLaterEntryList(final SeedCallback<Entry> seedCallback) {

        String streamId = FeedlyData.SAVED_FOR_LATER;
        int count = 1000;

        getStreamEntryListWithId(false, streamId, count, new SeedCallback<Entry>() {
            @Override
            public void onSuccess(List<Entry> feedlyDataList) {
                seedCallback.onSuccess(feedlyDataList);
            }

            @Override
            public void onException(String error) {

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


    public void getFeedListWithId(List<String> feedIdList, final SeedCallback<Feed> seedCallback) {

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

    public void getStreamEntryListWithId(boolean unreadOnly, String streamId, int count, final SeedCallback<Entry> seedCallback) {

        String href = rootUrl + "/v3/streams/ids?streamId=" + streamId + "&count=" + count + "&unreadOnly=" + String.valueOf(unreadOnly);

        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {

                StreamEntryList streamEntryList = new Gson().fromJson(httpResponse, StreamEntryList.class);

                getEntryListWithId(streamEntryList.ids, new SeedCallback<Entry>() {
                    @Override
                    public void onSuccess(List<Entry> feedlyDataList) {
                        seedCallback.onSuccess(feedlyDataList);
                    }

                    @Override
                    public void onException(String error) {

                    }
                });

            }

            @Override
            public void onException(Exception e) {

            }
        });


    }


}
