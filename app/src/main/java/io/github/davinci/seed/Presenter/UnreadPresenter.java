package io.github.davinci.seed.Presenter;

import java.util.List;

import io.github.davinci.seed.Model.Entity.UnreadCount;
import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.View.ViewInterface.UnreadView;

/**
 * Created by davinci42 on 15/10/22.
 */
public class UnreadPresenter {

    private UnreadView mView;

    private FeedlyNetwork mFeedNetWork = new FeedlyNetwork();

    private UnreadView getView() {
        return mView;
    }

    public void getUnreadCategoryList() {
        mFeedNetWork.getUnreadFeed(new SeedCallback<UnreadCount>() {
            @Override
            public void onSuccess(List<UnreadCount> feedlyDataList) {

            }

            @Override
            public void onException(Exception e) {

            }
        });
    }




}
