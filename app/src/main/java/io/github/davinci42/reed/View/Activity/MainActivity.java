package io.github.davinci42.reed.View.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import butterknife.Bind;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.Model.ReedNetUtils.SignHelper;
import io.github.davinci42.reed.MvpBase.BaseActivity;
import io.github.davinci42.reed.Presenter.MainPresenter;
import io.github.davinci42.reed.R;
import io.github.davinci42.reed.View.Adapter.PagerAdapter;
import io.github.davinci42.reed.View.Fragment.RecentlyFragment;
import io.github.davinci42.reed.View.Fragment.SavedFragment;
import io.github.davinci42.reed.View.Fragment.UnreadFragment;
import io.github.davinci42.reed.View.ViewInterface.MainView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

	@Bind(R.id.tb) Toolbar mToolbar;
	@Bind(R.id.vp) ViewPager mViewPager;
	@Bind(R.id.tl) TabLayout mTabLayout;
	private UnreadFragment mUnreadFragment;
	private RecentlyFragment mRecentlyFragment;
	private SavedFragment mSavedFragment;

	@Override public void updateView() {

	}

	@Override public void updateData() {

		if (SignHelper.getToken().isEmpty() || SignHelper.getUserId().isEmpty()) {
			Log.e("davinci42", "Please add your userId & token in SignHelper");
		} else {

			getPresenter().getUnreadFromNet();
			getPresenter().getRecentlyFromNet();
			getPresenter().getSavedFromNet();
		}
	}

	@Override public void initView() {
		initToolbar();
		initViewPager();
	}

	private void initToolbar() {
		mToolbar.setTitle(R.string.app_name);
		mToolbar.setTitleTextColor(Color.WHITE);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
	}

	private void initViewPager() {

		mUnreadFragment = new UnreadFragment();
		mRecentlyFragment = new RecentlyFragment();
		mSavedFragment = new SavedFragment();

		List<Fragment> fragList = new ArrayList<>();
		fragList.add(mUnreadFragment);
		fragList.add(mRecentlyFragment);
		fragList.add(mSavedFragment);

		PagerAdapter vpAdapter = new PagerAdapter(getSupportFragmentManager(), fragList);
		mViewPager.setAdapter(vpAdapter);

		mTabLayout.setupWithViewPager(mViewPager);

		mTabLayout.getTabAt(0).setText(R.string.unread_frag_name);
		mTabLayout.getTabAt(1).setText(R.string.recently_frag_name);
		mTabLayout.getTabAt(2).setText(R.string.saved_frag_name);
	}

	@Override public int getLayoutResId() {
		return R.layout.activity_main;
	}

	@Override public void initData() {

	}

	@Override protected MainPresenter createPresenter() {
		return new MainPresenter(this);
	}

	@Override public void updateUnreadData(List<Entry> entryList) {
		mUnreadFragment.updateDataFromNet(entryList);
	}

	@Override public void updateRecentlyData(List<Entry> entries) {
		mRecentlyFragment.updateDataFromNet(entries);
	}

	@Override public void updateSavedData(List<Entry> entries) {
		mSavedFragment.updateDataFromNet(entries);
	}
}
