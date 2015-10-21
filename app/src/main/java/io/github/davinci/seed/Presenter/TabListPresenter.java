package io.github.davinci.seed.Presenter;

import java.util.List;

import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;

/**
 * Created by davinci42 on 15/10/21.
 */
public class TabListPresenter {

    FeedlyNetwork mFeedlyNetwork = new FeedlyNetwork();

    public void getCategoryList() {

        mFeedlyNetwork.getCategoryList(new SeedCallback() {
            @Override
            public void onSuccess(List feedlyDataList) {

            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    public void getRecentlyRead() {
        mFeedlyNetwork.getRecentlyRead(new SeedCallback() {
            @Override
            public void onSuccess(List feedlyDataList) {

            }

            @Override
            public void onException(Exception e) {

            }
        });
    }
}
