package io.github.davinci.seed.Model.FeedlyNetUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import io.github.davinci.seed.Model.Entity.Category;
import io.github.davinci.seed.Model.Entity.Entry;
import io.github.davinci.seed.Model.Entity.FeedlyData;
import io.github.davinci.seed.Model.Utils.NetUtils;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.Model.Utils.SeedNetCallback;

/**
 * Created by davinci42 on 15/10/21.
 */
public class FeedlyNetwork {

    NetUtils mNetUtils = new NetUtils();

    String rootUrl = "http://sandbox.feedly.com";

    String userId;



    public void getCategoryList(final SeedCallback seedCallback) {

        String href = rootUrl + "/v3/categories";

        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String response) {
                List<Category> categoryList = new Gson().fromJson(response, new TypeToken<ArrayList<Category>>(){}.getType());
                seedCallback.onSuccess(categoryList);
            }

            @Override
            public void onException(Exception e) {
                seedCallback.onException(e);
            }
        });
    }

    public void getRecentlyRead(final SeedCallback seedCallback) {

        String href = rootUrl + "user" + SignHelper.getUserId() + "/tag/global.read";
        mNetUtils.doGet(href, new SeedNetCallback() {
            @Override
            public void onSuccess(String httpResponse) {
                List<Entry> entryList = new Gson().fromJson(httpResponse, new TypeToken<ArrayList<Entry>>(){}.getType());
                seedCallback.onSuccess(entryList);
            }

            @Override
            public void onException(Exception e) {
                seedCallback.onException(e);
            }
        });

    }

}
