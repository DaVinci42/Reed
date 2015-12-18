package io.github.davinci42.seed.View.Activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.Bind;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;
import io.github.davinci42.seed.MvpBase.BaseActivity;
import io.github.davinci42.seed.Presenter.MainPresenter;
import io.github.davinci42.seed.View.Adapter.PagerAdapter;
import io.github.davinci42.seed.View.Fragment.RecentlyReadFragment;
import io.github.davinci42.seed.View.Fragment.SavedForLaterFragment;
import io.github.davinci42.seed.View.Fragment.UnreadFragment;
import io.github.davinci42.seed.View.ViewInterface.MainView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

	@Bind(R.id.toolbar) Toolbar mToolbar;

	private UnreadFragment unreadFragment;
	private RecentlyReadFragment recentlyReadFragment;
	private SavedForLaterFragment savedForLaterFragment;

	@Override public void initData() {

		if (SignHelper.ifIdAndTokenReady()) {
			updateFeedDb();
		} else {
			Toast.makeText(MainActivity.this, "Empty userId & token", Toast.LENGTH_SHORT).show();
		}
	}

	private void updateFeedDb() {
		getPresenter().updateFeedDb();
	}

	@Override public void onFeedDbUpdated() {
		updateEntryDb();
	}

	@Override public void updateView() {
		initToolbar();
		initTabs();
	}

	private void initToolbar() {

		//setSupportActionBar(mToolbar);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
		mToolbar.setTitleTextColor(Color.WHITE);
		mToolbar.setTitle(R.string.app_name);
	}

	private void updateEntryDb() {
		// All AsyncRequest
		getPresenter().updateUnreadEntryDb();
		getPresenter().updateRecentlyEntryDb();
		getPresenter().updateSavedEntryDb();
	}

	@Override public int getLayoutResId() {
		return R.layout.view_main_activity;
	}

	private void initTabs() {

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

		unreadFragment = new UnreadFragment();
		recentlyReadFragment = new RecentlyReadFragment();
		savedForLaterFragment = new SavedForLaterFragment();

		List<Fragment> fragmentList = new ArrayList<>();
		fragmentList.add(unreadFragment);
		fragmentList.add(recentlyReadFragment);
		fragmentList.add(savedForLaterFragment);

		PagerAdapter viewPagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(viewPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
		tabLayout.setupWithViewPager(viewPager);

		tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_color));
		tabLayout.getTabAt(0).setIcon(R.drawable.ic_brightness_1_white_24dp);
		tabLayout.getTabAt(1).setIcon(R.drawable.ic_panorama_fish_eye_white_24dp);
		tabLayout.getTabAt(2).setIcon(R.drawable.ic_star_white_24dp);
	}

	@Override protected MainPresenter createPresenter() {
		MainPresenter mainPresenter = new MainPresenter();
		mainPresenter.getContext(this);
		return mainPresenter;
	}

	@Override public void onUnreadDbUpdated() {
		unreadFragment.getEntryListFromDb();
	}

	@Override public void onRecentlyDbUpdated() {
		recentlyReadFragment.getEntryListFromDb();
	}

	@Override public void onSavedDbUpdated() {
		savedForLaterFragment.getEntryListFromDb();
	}
}
