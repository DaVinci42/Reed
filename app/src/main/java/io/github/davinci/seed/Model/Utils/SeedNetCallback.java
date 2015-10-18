package io.github.davinci.seed.Model.Utils;

import com.squareup.okhttp.Response;

import io.github.davinci.seed.Model.Entity.SeedData;

/**
 * Created by ying on 10/18/15.
 */
public interface SeedNetCallback {
    void onSuccess(String response);
    void onException(Exception e);

}
