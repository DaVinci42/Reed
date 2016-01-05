package io.github.davinci42.reed.View.Activity;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import io.github.davinci42.reed.Model.LocalBean.ElvFeed;
import io.github.davinci42.reed.Model.LocalBean.ReedLocalData;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.MvpBase.BaseActivity;
import io.github.davinci42.reed.Presenter.FeedRvPresenter;
import io.github.davinci42.reed.R;
import io.github.davinci42.reed.View.Adapter.FeedRvAdapter;
import io.github.davinci42.reed.View.ViewInterface.FeedRvView;
import java.util.ArrayList;
import java.util.List;

public class FeedRvActivity extends BaseActivity<FeedRvView, FeedRvPresenter> implements FeedRvView {

	@Bind(R.id.tb) Toolbar mToolbar;
	@Bind(R.id.rv) RecyclerView mRv;

	private ElvFeed mFeed;
	private List<Entry> mEntryList = new ArrayList<>();
	private FeedRvAdapter mFeedRvAdapter;

	@Override public int getLayoutResId() {
		return R.layout.activity_feed_rv;
	}

	@Override protected FeedRvPresenter createPresenter() {
		return new FeedRvPresenter();
	}

	@Override public void initData() {
		mFeed = (ElvFeed) getIntent().getSerializableExtra(ReedLocalData.FEED_KEY);
		getPresenter().getEntryListWithIds(mFeed.entryIds);
	}

	@Override public void initView() {
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
		mToolbar.setTitle(mFeed.title);
		mToolbar.setTitleTextColor(Color.WHITE);

		mFeedRvAdapter = new FeedRvAdapter(this, mEntryList);
		mRv.setAdapter(mFeedRvAdapter);
		mRv.setLayoutManager(new LinearLayoutManager(this));
	}


	@Override public void updateView() {

	}

	@Override public void updateData() {

	}

	@Override public void refreshEntriesFromNet(List<Entry> entries) {
		mEntryList.clear();
		mEntryList.addAll(entries);

		runOnUiThread(new Runnable() {
			@Override public void run() {
				mFeedRvAdapter.notifyDataSetChanged();
			}
		});
	}
}
