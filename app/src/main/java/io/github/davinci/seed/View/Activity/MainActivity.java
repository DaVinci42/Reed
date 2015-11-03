package io.github.davinci.seed.View.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Window;

import java.util.HashMap;
import java.util.Map;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.MvpBase.MvpActivity;
import io.github.davinci.seed.Presenter.MainPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.PagerAdapter;
import io.github.davinci.seed.View.ViewInterface.MainView;


public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView{

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
        PagerAdapter viewPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);

        tabLayout.addTab(tabLayout.newTab().setText("Unread"));
        tabLayout.addTab(tabLayout.newTab().setText("RecentlyRead"));
        tabLayout.addTab(tabLayout.newTab().setText("SavedForLater"));

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
