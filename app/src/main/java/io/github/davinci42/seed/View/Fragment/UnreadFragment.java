package io.github.davinci42.seed.View.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.MvpBase.BaseFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci42.seed.View.Adapter.TabElvAdapter;
import io.github.davinci42.seed.View.Adapter.TabRvAdapter;
import io.github.davinci42.seed.View.Interface.OnListClickListener;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.List;

public class UnreadFragment extends BaseFragment<TabListView, TabListPresenter> implements TabListView {

	@Bind(R.id.elv) ExpandableListView mElv;
	@Bind(R.id.tv_empty_hint) TextView mTvEmptyHint;

	private List<String> mCategoryList = new ArrayList<>();
	private List<List<Entry>> mEntryList = new ArrayList<>();

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initElv();

		getEntryListFromDb();
	}

	private void initElv() {
		TabElvAdapter tabElvAdapter = new TabElvAdapter(getContext(), mCategoryList, mEntryList);
		mElv.setAdapter(tabElvAdapter);
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
		}

	}
}
