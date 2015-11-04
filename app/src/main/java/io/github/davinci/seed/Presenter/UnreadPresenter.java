package io.github.davinci.seed.Presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.UnreadCount;
import io.github.davinci.seed.Model.Entity.UnreadcountsEntity;
import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.MvpBase.MvpPresenter;
import io.github.davinci.seed.View.ViewInterface.UnreadView;

/**
 * Created by davinci42 on 15/10/22.
 */
public class UnreadPresenter extends MvpPresenter<UnreadView>{

    private UnreadView mView;

    private FeedlyNetwork mFeedNetWork = new FeedlyNetwork();

    public UnreadView getView() {
        return mView;
    }

    public void getUnreadCategoryList(final HashMap<String, CategoryWithFeeds> categoryAllMap) {
        mFeedNetWork.getUnreadFeed(new SeedCallback<UnreadcountsEntity>() {
            @Override
            public void onSuccess(List<UnreadcountsEntity> feedlyDataList) {

                for (UnreadcountsEntity unreadEntity : feedlyDataList) {

                    if (unreadEntity.id.startsWith("user/") && !unreadEntity.id.endsWith("/global.all")) {
                        // Category
                        categoryAllMap.get(unreadEntity.id).count = unreadEntity.count;
                    } else {
                        // Feed
                        for (Map.Entry<String, CategoryWithFeeds> categoryEntity : categoryAllMap.entrySet()) {
                            for (Feed feed : categoryEntity.getValue().feedList) {
                                if (feed.id.equals(unreadEntity.id)) {
                                    feed.count = unreadEntity.count;
                                }
                            }
                        }
                    }
                }

                // getView.updateUnread(categoryAllMap);
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }


}
