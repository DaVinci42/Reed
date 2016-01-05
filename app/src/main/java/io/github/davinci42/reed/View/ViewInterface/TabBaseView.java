package io.github.davinci42.reed.View.ViewInterface;

import io.github.davinci42.reed.Model.LocalBean.ElvCategory;
import io.github.davinci42.reed.MvpBase.BaseView;
import java.util.Map;

/**
 * Created by davinci42 on 2016/1/4.
 */
public interface TabBaseView extends BaseView {

	void refreshCategoryMap(Map<String, ElvCategory> categoryMap);
}
