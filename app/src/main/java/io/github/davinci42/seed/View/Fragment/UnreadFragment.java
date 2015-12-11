package io.github.davinci42.seed.View.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BaseFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci42.seed.View.Adapter.TabElvAdapter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnreadFragment extends BaseFragment<TabListView, TabListPresenter> implements TabListView {

	@Bind(R.id.elv) ExpandableListView mElv;
	@Bind(R.id.tv_empty_hint) TextView mTvEmptyHint;

	private Map<String, List<Entry>> mCatgoryMap = new HashMap<>();
	private List<String> mCategoryList = new ArrayList<>();
	private List<List<Entry>> mEntryList = new ArrayList<>();
	private TabElvAdapter mTabElvAdapter;

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initElv();

		getEntryListFromDb();
	}

	private void initElv() {
		mTabElvAdapter = new TabElvAdapter(getContext(), mCategoryList, mEntryList);
		mElv.setAdapter(mTabElvAdapter);
	}

	@Override protected TabListPresenter createPresenter() {
		return new TabListPresenter();
	}

	@Override public int getLayoutResId() {
		return R.layout.layout_fragment;
	}

	public void getEntryListFromDb() {
		getPresenter().updateUnreadList(getContext());
	}

	@Override public void updateEsData(List<Entry> entries) {
		if (entries.isEmpty()) {
			mElv.setVisibility(View.GONE);
			mTvEmptyHint.setVisibility(View.VISIBLE);
		} else {
			mElv.setVisibility(View.VISIBLE);
			mTvEmptyHint.setVisibility(View.GONE);

			for (Entry entry : entries) {
				if (!entry.categoryList.isEmpty()) {
					for (String category : entry.categoryList) {
						if (!mCatgoryMap.containsKey(category)) {
							// map 中没有对应 category
							List<Entry> entryList = new ArrayList<>();
							entryList.add(entry);
							mCatgoryMap.put(category, entryList);
						} else {
							// map 中已存在对应 category,直接添加 entry
							mCatgoryMap.get(category).add(entry);
						}
					}
				}
			}

			for (String category : mCatgoryMap.keySet()) {
				mCategoryList.clear();
				mEntryList.clear();
				mCategoryList.add(category);
				List<Entry> entryList = new ArrayList<>();
				for (Entry entry : mCatgoryMap.get(category)) {
					entryList.add(entry);
				}
				mEntryList.add(entryList);
			}
			mTabElvAdapter.notifyDataSetChanged();
		}
	}
}
