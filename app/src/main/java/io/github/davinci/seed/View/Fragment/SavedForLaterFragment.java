package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.MvpBase.MvpView;
import io.github.davinci.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.ViewInterface.TabListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedForLaterFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.widget_rv, container, false);
    }

    @Override
    protected TabListPresenter createPresenter() {
        return new TabListPresenter();
    }
}
