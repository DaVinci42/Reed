package io.github.davinci42.seed.View.ViewInterface;

import java.util.HashMap;

import io.github.davinci42.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci42.seed.MvpBase.MvpView;

/**
 * Created by davinci42 on 15/10/26.
 */
public interface MainView extends MvpView {
    void updateCategoryMap(HashMap<String, CategoryWithFeeds> hashMap);
    void onEmptyToken();
}
