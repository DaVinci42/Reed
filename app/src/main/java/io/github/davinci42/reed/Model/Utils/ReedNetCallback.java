package io.github.davinci42.reed.Model.Utils;

/**
 * Created by davinci42 on 15/10/21.
 */
public interface ReedNetCallback {

    void onSuccess(String httpResponse);
    void onException(Exception e);
}
