package io.github.davinci.seed.View.ViewInterface;

import java.util.HashMap;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;

/**
 * Created by davinci42 on 15/10/26.
 */
public interface MainView {
    void updateCategoryMap(HashMap<String, CategoryWithFeeds> hashMap);
}
