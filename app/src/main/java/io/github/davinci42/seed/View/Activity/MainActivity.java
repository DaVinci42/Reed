package io.github.davinci42.seed.View.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.github.davinci42.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;
import io.github.davinci42.seed.MvpBase.MvpActivity;
import io.github.davinci42.seed.Presenter.MainPresenter;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.View.Adapter.PagerAdapter;
import io.github.davinci42.seed.View.Fragment.RecentlyReadFragment;
import io.github.davinci42.seed.View.Fragment.SavedForLaterFragment;
import io.github.davinci42.seed.View.Fragment.UnreadFragment;
import io.github.davinci42.seed.View.ViewInterface.MainView;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    private HashMap<String, CategoryWithFeeds> mHashMap;


    @Override
    public void initData() {
        if (SignHelper.ifIdAndTokenReady()) {
            updateAllDb();
        } else {
            Toast.makeText(MainActivity.this, "Empty userId & token in SignHelper", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateView() {

    }


    private void updateAllDb() {
        getPresenter().updateFeedDb();
        getPresenter().updateUnreadEntryDb();
        getPresenter().updateRecentlyEntryDb();
        getPresenter().updateSavedEntryDb();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.view_main_activity;
    }

    private void initTabs() {

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new UnreadFragment());
        fragmentList.add(new RecentlyReadFragment());
        fragmentList.add(new SavedForLaterFragment());

        Bundle bundle = new Bundle();
        bundle.putSerializable(FeedlyData.FEED_MAP, mHashMap);

        for (Fragment fragment : fragmentList) {
            fragment.setArguments(bundle);
        }

        PagerAdapter viewPagerAdapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Unread");
        tabLayout.getTabAt(1).setText("Recently");
        tabLayout.getTabAt(2).setText("Saved");

    }


    @Override
    public void updateCategoryMap(HashMap<String, CategoryWithFeeds> hashMap) {
        mHashMap = hashMap;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initTabs();
            }
        });

    }


    @Override
    protected MainPresenter createPresenter() {
        MainPresenter mainPresenter = new MainPresenter();
        mainPresenter.getContext(this);
        return mainPresenter;
    }

    @Override
    public void onEmptyToken() {
        Toast.makeText(MainActivity.this, "Empty UserId & Token in SignHelper", Toast.LENGTH_LONG).show();
    }


}
