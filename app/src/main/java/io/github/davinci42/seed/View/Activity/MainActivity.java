package io.github.davinci42.seed.View.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
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

	private UnreadFragment unreadFragment;
	private RecentlyReadFragment recentlyReadFragment;
	private SavedForLaterFragment savedForLaterFragment;

	@Override public void initData() {
		if (SignHelper.ifIdAndTokenReady()) {
			updateFeedDb();
			initTabs();
		} else {
			Toast.makeText(MainActivity.this, "Empty userId & token in SignHelper", Toast.LENGTH_SHORT).show();
		}
	}

	private void updateFeedDb() {
		getPresenter().updateFeedDb();
	}

	@Override public void onFeedDbUpdated() {
		updateEntryDb();
	}

	@Override public void updateView() {

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
		tabLayout.getTabAt(0).setText("Unread");
		tabLayout.getTabAt(1).setText("Recently");
		tabLayout.getTabAt(2).setText("Saved");
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

	}

	@Override public void onSavedDbUpdated() {

	}
}
