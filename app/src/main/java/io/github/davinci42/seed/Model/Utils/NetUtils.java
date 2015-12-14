package io.github.davinci42.seed.Model.Utils;

import android.util.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;
import java.io.IOException;

/**
 * Created by ying on 10/18/15.
 */
public class NetUtils {

	final OkHttpClient client = new OkHttpClient();

	String token = SignHelper.getToken();

	final static MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");

	public void doGet(String href, final SeedNetCallback seedNetCallback) {
		Request request = new Request.Builder().url(href).addHeader("Authorization", "OAuth " + token).build();

		client.newCall(request).enqueue(new Callback() {
			@Override public void onFailure(Request request, IOException e) {
				seedNetCallback.onException(e);
			}

			@Override public void onResponse(Response response) throws IOException {
				if (response.isSuccessful()) {
					seedNetCallback.onSuccess(response.body().string());
				} else {
					Log.e("NetUtils not success: ", response.toString());
				}
			}
		});
	}

	public void doPost(String href, String postData, final SeedNetCallback seedNetCallback) {

		try {
			Request request = new Request.Builder().url(href)
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postData))
				.addHeader("Authorization", "OAuth " + token)
				.build();

			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				seedNetCallback.onSuccess(response.body().string());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
