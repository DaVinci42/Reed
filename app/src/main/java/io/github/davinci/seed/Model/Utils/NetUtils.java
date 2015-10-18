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

import io.github.davinci.seed.Model.Entity.SeedData;

/**
 * Created by ying on 10/18/15.
 */
public class NetUtils {

    final OkHttpClient client = new OkHttpClient();

    final static MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/json; charset=utf-8");

    public void doGet(String href, final SeedNetCallback seedNetCallback) throws Exception{
        Request request = new Request.Builder()
                .url(href)
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

    public void doPost(String href, SeedData postData, final SeedNetCallback seedNetCallback) throws Exception{

        String postBody = new Gson().toJson(postData);

        Request request = new Request.Builder()
                .url(href)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            seedNetCallback.onSuccess(response.body().string());
        } else {
            Log.e("NetErr", response.body().string());
        }
    }
}
