package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Presenter.UnreadPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.PagerAdapter;
import io.github.davinci.seed.View.ViewInterface.UnreadView;


public class UnreadFragment extends Fragment implements UnreadView{

    private RecyclerView mRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        initRv();
        return inflater.inflate(R.layout.widget_rv, container, false);
    }

    private void initRv() {
        RecyclerView mRv = (RecyclerView) getActivity().findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());


    }

    private UnreadPresenter getPresenter() {
        return new UnreadPresenter();
    }


}
