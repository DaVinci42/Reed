package io.github.davinci42.seed.Model.FeedlyNetUtils;



/**
 * Created by davinci42 on 15/10/21.
 */
public class SignHelper {

    public static String getUserId() {
        return "";    }


    public static String getToken() {
        return "";    }


    public static boolean ifIdAndTokenReady() {

        return (!getToken().isEmpty() && !getUserId().isEmpty());
    }
}
