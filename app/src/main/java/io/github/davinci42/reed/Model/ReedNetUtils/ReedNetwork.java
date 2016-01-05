package io.github.davinci42.reed.Model.ReedNetUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.Model.NetBean.ReedNetData;
import io.github.davinci42.reed.Model.NetBean.StreamIdResponse;
import io.github.davinci42.reed.Model.NetBean.Subscription;
import io.github.davinci42.reed.Model.Utils.NetUtils;
import io.github.davinci42.reed.Model.Utils.ReedCallback;
import io.github.davinci42.reed.Model.Utils.ReedNetCallback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davinci42 on 15/10/21.
 */
public class ReedNetwork {

	NetUtils mNetUtils = new NetUtils();

	String rootUrl = "http://cloud.feedly.com";

	String userId = SignHelper.getUserId();

	private int count = 100;

	public void getSubscriptionList(final ReedCallback<Subscription> seedCallback) {

		final String href = rootUrl + "/v3/subscriptions";
		mNetUtils.doGet(href, new ReedNetCallback() {
			@Override public void onSuccess(String httpResponse) {

				List<Subscription> subscriptionList =
					new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Subscription>>() {
					}.getType());
				seedCallback.onSuccess(subscriptionList);
			}

			@Override public void onException(Exception e) {
				seedCallback.onException(String.valueOf(e));
			}
		});
	}

	public void getUnreadEntryList(final ReedCallback<Entry> seedCallback) {
		String streamId = ReedNetData.GLOBAL_ALL;
		getStreamEntryListWithId(true, streamId, count, new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				seedCallback.onSuccess(feedlyDataList);
			}

			@Override public void onException(String error) {
				seedCallback.onException(error);
			}
		});
	}

	public void getRecentlyEntryList(final ReedCallback<Entry> seedCallback) {

		String streamId = ReedNetData.GLOBAL_READ;

		getStreamEntryListWithId(false, streamId, count, new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				seedCallback.onSuccess(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	public void getSavedForLaterEntryList(final ReedCallback<Entry> seedCallback) {

		String streamId = ReedNetData.GLOBAL_SAVED;

		getStreamEntryListWithId(false, streamId, count, new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				seedCallback.onSuccess(feedlyDataList);
			}

			@Override public void onException(String error) {

			}
		});
	}

	public void getEntryListWithId(List<String> entryIdList, final ReedCallback<Entry> seedCallback) {

		String postData = new Gson().toJson(entryIdList);

		String href = rootUrl + "/v3/entries/.mget";

		try {
			mNetUtils.doPost(href, postData, new Callback() {
				@Override public void onFailure(Request request, IOException e) {
					e.printStackTrace();
					seedCallback.onException(e.toString());
				}

				@Override public void onResponse(Response response) throws IOException {
					List<Entry> entries =
						new Gson().fromJson(response.body().string(), new TypeToken<ArrayList<Entry>>() {
						}.getType());
					seedCallback.onSuccess(entries);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getStreamEntryListWithId(boolean unreadOnly, String streamId, int count,
		final ReedCallback<Entry> seedCallback) {

		String href =
			rootUrl + "/v3/streams/ids?streamId=" + streamId + "&count=" + count + "&unreadOnly=" + String.valueOf(
				unreadOnly);

		mNetUtils.doGet(href, new ReedNetCallback() {
			@Override public void onSuccess(String httpResponse) {

				StreamIdResponse streamEntryList = new Gson().fromJson(httpResponse, StreamIdResponse.class);

				getEntryListWithId(streamEntryList.ids, new ReedCallback<Entry>() {
					@Override public void onSuccess(List<Entry> feedlyDataList) {
						seedCallback.onSuccess(feedlyDataList);
					}

					@Override public void onException(String error) {
						seedCallback.onException(error);
					}
				});
			}

			@Override public void onException(Exception e) {
				seedCallback.onException(e.toString());
			}
		});
	}
}
