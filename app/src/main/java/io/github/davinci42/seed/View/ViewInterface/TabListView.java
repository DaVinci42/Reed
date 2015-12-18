package io.github.davinci42.seed.View.ViewInterface;

import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BaseView;
import java.util.List;

/**
 * Created by davinci42 on 15/11/4.
 */
public interface TabListView extends BaseView {

	void updateElvData(List<Entry> entries);

}


