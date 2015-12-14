package io.github.davinci42.seed.View.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BaseFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyReadFragment extends BaseFragment<TabListView, TabListPresenter> implements TabListView {

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override protected TabListPresenter createPresenter() {
		return new TabListPresenter();
	}

	@Override public int getLayoutResId() {

		return R.layout.view_fragment;
	}

	@Override public void updateElvData(List<Entry> entries) {

	}
}
