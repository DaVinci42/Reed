package io.github.davinci.seed.Presenter;

import java.util.List;

import io.github.davinci.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.MvpBase.MvpPresenter;
import io.github.davinci.seed.View.ViewInterface.TabListView;

/**
 * Created by davinci42 on 15/11/4.
 */
public class TabListPresenter extends MvpPresenter<TabListView> {

    private FeedlyNetwork mFeedlyNetwork = new FeedlyNetwork();

    public void getUnreadFeedList() {

        mFeedlyNetwork.getUnreadFeed(new SeedCallback<UnreadCountsEntity>() {
            @Override
            public void onSuccess(List<UnreadCountsEntity> feedlyDataList) {

                getView().updateCategoryMap(feedlyDataList);
            }

            @Override
            public void onException(Exception e) {

            }
        });

    }
}
