package io.github.davinci.seed.View.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import io.github.davinci.seed.View.Fragment.RecentlyReadFragment;
import io.github.davinci.seed.View.Fragment.SavedForLaterFragment;
import io.github.davinci.seed.View.Fragment.UnreadFragment;

/**
 * Created by davinci42 on 15/10/21.
 */
public class PagerAdapter extends FragmentStatePagerAdapter{

    private FragmentManager mFm;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        mFm = fm;
    }


    @Override
    public Fragment getItem(int position) {
        return mFm.getFragments().get(position);
    }


    @Override
    public int getCount() {
        return mFm.getFragments().size();
    }
}
