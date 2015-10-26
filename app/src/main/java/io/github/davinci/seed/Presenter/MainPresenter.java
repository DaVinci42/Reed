package io.github.davinci.seed.Presenter;


import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.davinci.seed.Model.Entity.Category;
import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.Subscription;
import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.View.ViewInterface.MainView;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter {

    private MainView mView;
    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

    public void updateCategoryFeedMap() {

        mFeedNetwork.updateCategoryFeedMap(new SeedCallback<Subscription>() {
            @Override
            public void onSuccess(List<Subscription> feedlyDataList) {
                HashMap<String, CategoryWithFeeds> map = new HashMap<>();
                map.put("Other", new CategoryWithFeeds());

                for (Subscription subs : feedlyDataList) {

                    if (!subs.categories.isEmpty()) {
                        Feed feed = new Feed();
                        for (Category category : subs.categories) {
                            if (!map.containsKey(category.label)) {
                                map.put(category.id, new CategoryWithFeeds());
                            } else {
                                feed.id = subs.id;
                                feed.title = subs.title;
                                feed.website = subs.website;
                                map.get(category.id).feedList.add(feed);
                            }
                        }
                    } else {
                        // Feed without category, put them in a new category called Others
                        Feed feed = new Feed();
                        feed.id = subs.id;
                        feed.title = subs.title;
                        feed.website = subs.website;
                        map.get("Others").feedList.add(feed);
                    }
                }

                for (Map.Entry<String, CategoryWithFeeds> entry : map.entrySet()) {
                    Log.e("davinci42", "Category: " + entry.getKey() + "    contains" + entry.getValue().feedList.size());
                }

                if (getView() != null) {
                    getView().updateCategoryMap(map);
                }

            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    private MainView getView() {
        return mView;
    }
}
