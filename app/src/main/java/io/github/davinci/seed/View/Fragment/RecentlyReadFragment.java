package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.ViewInterface.TabListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyReadFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    protected TabListPresenter createPresenter() {
        return new TabListPresenter();
    }


    @Override
    public void updateCategoryMap(List dataList) {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_tab;
    }
}
