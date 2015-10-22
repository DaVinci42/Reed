package io.github.davinci.seed.View.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.PagerAdapter;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main_activity);

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


}
