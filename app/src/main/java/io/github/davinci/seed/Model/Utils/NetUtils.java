package io.github.davinci.seed.Model.Utils;

import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import io.github.davinci.seed.Model.Entity.FeedlyData;
import io.github.davinci.seed.Model.FeedlyNetUtils.SignHelper;

/**
 * Created by ying on 10/18/15.
 */
public class NetUtils {

    final OkHttpClient client = new OkHttpClient();

    String token = SignHelper.getToken();

    final static MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("application/json; charset=utf-8");

    public void doGet(String href, final SeedNetCallback seedNetCallback) {
        Request request = new Request.Builder()
                .url(href)
                .addHeader("Authorization", "OAuth " + token)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                seedNetCallback.onException(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                seedNetCallback.onSuccess(response.body().string());
            }
        });

    }

    public void doPost(String href, String postData, final SeedNetCallback seedNetCallback) {

        try {
            Request request = new Request.Builder()
                    .url(href)
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postData))
                    .addHeader("Authorization", "OAuth " + token)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                seedNetCallback.onSuccess(response.body().string());
            } else {
                Log.e("NetErr", response.body().string());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
