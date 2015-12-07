package io.github.davinci42.seed.View.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.MvpBase.BaseFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci42.seed.View.Adapter.TabRvAdapter;
import io.github.davinci42.seed.View.Interface.OnListClickListener;
import io.github.davinci42.seed.View.ViewInterface.TabListView;
import java.util.ArrayList;
import java.util.List;

public class UnreadFragment extends BaseFragment<TabListView, TabListPresenter> implements TabListView {

	private List<TabListItem> mTabItemList = new ArrayList<>();

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initRv();

		onUnreadDbUpdated();
	}

	private void initRv() {
		RecyclerView mRv = (RecyclerView) getActivity().findViewById(R.id.rv);
		RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
		mRv.setLayoutManager(mLayoutManager);
		TabRvAdapter mRvAdapter = new TabRvAdapter(mTabItemList, mOnFeedListClickListener);
		mRv.setAdapter(mRvAdapter);
	}

	public OnListClickListener mOnFeedListClickListener = new OnListClickListener() {
		@Override public void onClick(View v) {
		}
	};

	@Override protected TabListPresenter createPresenter() {
		return new TabListPresenter();
	}

	@Override public int getLayoutResId() {
		return R.layout.widget_rv;
	}

	public void onUnreadDbUpdated() {
		getPresenter().updateUnreadList();
	}

	@Override public void updateEsData() {

	}
}
