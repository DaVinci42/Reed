package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.davinci.seed.MvpBase.CoreFragment;
import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.MvpBase.MvpPresenter;
import io.github.davinci.seed.Presenter.RecentlyReadPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.ViewInterface.RecentlyReadView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentlyReadFragment extends MvpFragment<RecentlyReadView, RecentlyReadPresenter> implements RecentlyReadView {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    protected RecentlyReadPresenter createPresenter() {
        return new RecentlyReadPresenter();
    }
}
