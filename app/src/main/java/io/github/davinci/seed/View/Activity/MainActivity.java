package io.github.davinci.seed.View.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.MvpBase.MvpActivity;
import io.github.davinci.seed.Presenter.MainPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.PagerAdapter;
import io.github.davinci.seed.View.Fragment.RecentlyReadFragment;
import io.github.davinci.seed.View.Fragment.SavedForLaterFragment;
import io.github.davinci.seed.View.Fragment.UnreadFragment;
import io.github.davinci.seed.View.ViewInterface.MainView;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {

    private HashMap<String, CategoryWithFeeds> mHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main_activity);

        getPresenter().updateCategoryFeedMap();

        initTabs();
    }

    private void initTabs() {

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        FragmentManager mFm = getSupportFragmentManager();

        mFm.beginTransaction().add(R.id.viewpager, new UnreadFragment()).commit();
        mFm.beginTransaction().add(R.id.viewpager, new RecentlyReadFragment()).commit();
        mFm.beginTransaction().add(R.id.viewpager, new SavedForLaterFragment()).commit();

        Log.e("davinci42", "getFragments() is null? " + String.valueOf(mFm.getFragments() == null));



        Log.e("davinci42", "Fragments num: " + mFm.getFragments().size());

        PagerAdapter viewPagerAdapter = new PagerAdapter(mFm);
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
    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
