package io.github.davinci42.seed.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.google.gson.Gson;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BaseFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci42.seed.View.Adapter.TabElvAdapter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 2015/12/17.
 */
public abstract class TabListFragment extends BaseFragment<TabListView, TabListPresenter> implements TabListView {

	ExpandableListView mElv;
	TextView mTvEmptyView;

	@Override public int getLayoutResId() {
		return R.layout.layout_fragment;
	}

	@Override protected TabListPresenter createPresenter() {
		return new TabListPresenter();
	}

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mElv = getElv();
		mTvEmptyView = getEmptyView();

		initElv();
		getEntryListFromDb();
	}

	@Override public void updateElvData(List<Entry> entries) {

		final int count = entries.size();
		List<String> categoryList = getCategoryList();
		List<List<Entry>> entryAllList = getEntryList();

		getActivity().runOnUiThread(new Runnable() {
			@Override public void run() {
				if (count == 0) {
					mElv.setVisibility(View.GONE);
					mTvEmptyView.setVisibility(View.VISIBLE);
				} else {
					mElv.setVisibility(View.VISIBLE);
					mTvEmptyView.setVisibility(View.GONE);
				}
			}
		});

		if (count != 0) {

			getCategoryMap().clear();
			categoryList.clear();
			entryAllList.clear();

			for (Entry entry : entries) {
				if (!entry.categoryList.isEmpty()) {
					for (String category : entry.categoryList) {
						if (!getCategoryMap().containsKey(category)) {
							// map 中没有对应 category
							List<Entry> entryList = new ArrayList<>();
							entryList.add(entry);
							getCategoryMap().put(category, entryList);
						} else {
							// map 中已存在对应 category,直接添加 entry
							getCategoryMap().get(category).add(entry);
						}
					}
				}
			}

			for (String category : getCategoryMap().keySet()) {
				categoryList.add(category);
				List<Entry> entryList = new ArrayList<>();
				for (Entry entry : getCategoryMap().get(category)) {
					entryList.add(entry);
				}
				entryAllList.add(entryList);
			}

			getActivity().runOnUiThread(new Runnable() {
				@Override public void run() {
					getTabElvAdapter().notifyDataSetChanged();
				}
			});
		}
	}

	public abstract void getEntryListFromDb();

	protected void initElv() {
		setTabElvAdapter(new TabElvAdapter(getContext(), getCategoryList(), getEntryList()));
		mElv.setAdapter(getTabElvAdapter());
	}

	public abstract Map<String, List<Entry>> getCategoryMap();

	public abstract List<String> getCategoryList();

	public abstract List<List<Entry>> getEntryList();

	public abstract TabElvAdapter getTabElvAdapter();

	public abstract void setTabElvAdapter(TabElvAdapter adapter);

	public abstract ExpandableListView getElv();

	public abstract TextView getEmptyView();
}
