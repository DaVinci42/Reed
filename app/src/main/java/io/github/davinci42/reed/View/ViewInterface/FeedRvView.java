package io.github.davinci42.reed.View.ViewInterface;

import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.MvpBase.BaseView;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public interface FeedRvView extends BaseView {
	void refreshEntriesFromNet(List<Entry> entryList);
}
