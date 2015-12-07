package io.github.davinci42.seed.Presenter;

import io.github.davinci42.seed.MvpBase.BasePresenter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;

/**
 * Created by davinci42 on 15/11/4.
 */
public class TabListPresenter extends BasePresenter<TabListView> {

	public void updateUnreadList() {
		getView().updateEsData();
	}

	public void updateRecentlyList() {
		getView().updateEsData();
	}

	public void updateSavedList() {
		getView().updateEsData();
	}
}
