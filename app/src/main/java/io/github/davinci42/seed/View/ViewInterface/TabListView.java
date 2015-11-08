package io.github.davinci42.seed.View.ViewInterface;


import java.util.List;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.MvpBase.MvpView;

/**
 * Created by davinci42 on 15/11/4.
 */
public interface TabListView<T extends FeedlyData> extends MvpView {

    void updateCategoryMap(List<T> dataList);

}

