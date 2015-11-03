package io.github.davinci.seed.View.ViewInterface;

import java.util.HashMap;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.MvpBase.MvpView;

/**
 * Created by davinci42 on 15/10/26.
 */
public interface MainView extends MvpView{
    void updateCategoryMap(HashMap<String, CategoryWithFeeds> hashMap);
}
