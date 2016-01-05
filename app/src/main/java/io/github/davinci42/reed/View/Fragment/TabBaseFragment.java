package io.github.davinci42.reed.View.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import butterknife.Bind;
import io.github.davinci42.reed.Model.LocalBean.ElvCategory;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.MvpBase.BaseFragment;
import io.github.davinci42.reed.Presenter.TabBasePresenter;
import io.github.davinci42.reed.R;
import io.github.davinci42.reed.View.Adapter.ElvAdapter;
import io.github.davinci42.reed.View.ViewInterface.TabBaseView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 2016/1/4.
 */
public abstract class TabBaseFragment extends BaseFragment<TabBaseView, TabBasePresenter> implements TabBaseView {

	@Bind(R.id.elv) ExpandableListView mElv;

	private Map<String, ElvCategory> mCategoryMap = new HashMap<>();
	private ElvAdapter elvAdapter;

	@Override public int getLayoutResId() {
		return R.layout.fragment_elv;
	}

	private void initElv() {
		elvAdapter = new ElvAdapter(getContext(), mCategoryMap);
		mElv.setAdapter(elvAdapter);
		View emptyView = LayoutInflater.from(getContext())
			.inflate(R.layout.widget_elv_empty_view, (ViewGroup) mElv.getParent(), false);
		mElv.setEmptyView(emptyView);
	}

	@Override protected TabBasePresenter createPresenter() {
		return new TabBasePresenter();
	}

	public abstract void updateDataFromNet(List<Entry> entries);

	public void updateCategoryMap(Map<String, ElvCategory> categoryMap) {
		mCategoryMap.clear();
		mCategoryMap.putAll(categoryMap);
		elvAdapter.setData(getContext(), mCategoryMap);
		getActivity().runOnUiThread(new Runnable() {
			@Override public void run() {
				elvAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override public void updateView() {

	}

	@Override public void updateData() {

	}

	@Override public void initView() {
		initElv();
	}

	@Override public void initData() {

	}
}
