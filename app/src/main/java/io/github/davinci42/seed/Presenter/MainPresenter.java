package io.github.davinci42.seed.Presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;
import io.github.davinci42.seed.Model.Entity.Category;
import io.github.davinci42.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci42.seed.Model.Entity.Feed;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.Subscription;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;
import io.github.davinci42.seed.Model.Utils.SeedCallback;
import io.github.davinci42.seed.MvpBase.MvpPresenter;
import io.github.davinci42.seed.View.ViewInterface.MainView;

/**
 * Created by davinci42 on 15/10/26.
 */
public class MainPresenter extends MvpPresenter<MainView>{

    private FeedlyNetwork mFeedNetwork = new FeedlyNetwork();

    public void updateCategoryFeedMap() {

        if (SignHelper.ifIdAndTokenReady()) {

            Log.e("davinci42", "Id && Token Got!");

            mFeedNetwork.updateCategoryFeedMap(new SeedCallback<Subscription>() {
                @Override
                public void onSuccess(List<Subscription> feedlyDataList) {

                    HashMap<String, CategoryWithFeeds> map = new HashMap<>();

                    CategoryWithFeeds categoryAll =  new CategoryWithFeeds();
                    categoryAll.id = FeedlyData.ALL_CATEGORY_ID;
                    categoryAll.label = FeedlyData.ALL_CATEGORY_LABEL;
                    map.put(categoryAll.id, categoryAll);

                    CategoryWithFeeds uncategorized =  new CategoryWithFeeds();
                    uncategorized.id = FeedlyData.UNCATEGORIZED_ID;
                    uncategorized.label = FeedlyData.UNCATEGORIZED_LABEL;
                    map.put(uncategorized.id, uncategorized);

                    for (Subscription subs : feedlyDataList) {

                        if (!subs.categories.isEmpty()) {

                            Feed feed = new Feed();

                            feed.id = subs.id;
                            feed.title = subs.title;
                            feed.website = subs.website;

                            map.get(categoryAll.id).count += feed.count;
                            map.get(categoryAll.id).feedList.add(feed);

                            for (Category category : subs.categories) {

                                if (!map.containsKey(category.id)) {

                                    CategoryWithFeeds categoryWithFeeds = new CategoryWithFeeds();
                                    categoryWithFeeds.id = category.id;
                                    categoryWithFeeds.label = category.label;
                                    categoryWithFeeds.feedList.add(feed);

                                    map.put(category.id, categoryWithFeeds);

                                } else {
                                    map.get(category.id).feedList.add(feed);
                                }
                            }

                        } else {

                            // Feed without category, put them in a new category called uncategorized
                            Feed feed = new Feed();
                            feed.id = subs.id;
                            feed.title = subs.title;
                            feed.website = subs.website;

                            map.get(uncategorized.id).count += feed.count;
                            map.get(uncategorized.id).feedList.add(feed);
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

        } else {
            getView().onEmptyToken();
        }


    }

}
