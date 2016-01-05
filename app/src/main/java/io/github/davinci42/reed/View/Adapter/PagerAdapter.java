package io.github.davinci42.reed.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;

/**
 * Created by davinci42 on 15/10/21.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
