package io.github.davinci.seed.Model.Utils;

/**
 * Created by davinci42 on 15/10/21.
 */
public interface SeedNetCallback {

    void onSuccess(String httpResponse);
    void onException(Exception e);
}
