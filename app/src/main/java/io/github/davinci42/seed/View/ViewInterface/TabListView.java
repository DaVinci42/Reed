package io.github.davinci42.seed.View.ViewInterface;

import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.MvpBase.BaseView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davinci42 on 15/11/4.
 */
public interface TabListView<T extends FeedlyData> extends BaseView {

	void updateEsData();

}

