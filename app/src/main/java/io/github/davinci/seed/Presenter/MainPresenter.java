package io.github.davinci.seed.Presenter;

import java.util.HashMap;
import java.util.List;
import io.github.davinci.seed.Model.Entity.Category;
import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.Subscription;
import io.github.davinci.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci.seed.Model.Utils.SeedCallback;
import io.github.davinci.seed.MvpBase.MvpPresenter;
import io.github.davinci.seed.View.ViewInterface.MainView;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter extends MvpPresenter<MainView>{

    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

    public void updateCategoryFeedMap() {

        mFeedNetwork.updateCategoryFeedMap(new SeedCallback<Subscription>() {
            @Override
            public void onSuccess(List<Subscription> feedlyDataList) {

                HashMap<String, CategoryWithFeeds> map = new HashMap<>();

                map.put("Others", new CategoryWithFeeds());

                for (Subscription subs : feedlyDataList) {

                    if (!subs.categories.isEmpty()) {

                        Feed feed = new Feed();

                        for (Category category : subs.categories) {

                            if (!map.containsKey(category.id)) {

                                CategoryWithFeeds categoryWithFeeds = new CategoryWithFeeds();
                                categoryWithFeeds.id = category.id;
                                categoryWithFeeds.label = category.label;

                                feed.id = subs.id;
                                feed.title = subs.title;
                                feed.website = subs.website;
                                categoryWithFeeds.feedList.add(feed);

                                map.put(category.id, categoryWithFeeds);

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

                if (getView() != null) {
                    getView().updateCategoryMap(map);
                }

            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

}
