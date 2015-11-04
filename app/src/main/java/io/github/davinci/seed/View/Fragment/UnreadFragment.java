package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.RvAdapter;
import io.github.davinci.seed.View.ViewInterface.TabListView;


public class UnreadFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {

    @Bind(R.id.rv)
    RecyclerView mRv;

    private RvAdapter mRvAdapter;

    private HashMap<String, CategoryWithFeeds> mHashMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getPresenter().getUnreadFeedList();
        initRv();
        ButterKnife.bind(getActivity());
        return inflater.inflate(R.layout.widget_rv, container, false);
    }

    private void initRv() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mRvAdapter = new RvAdapter(mHashMap);
        mRv.setAdapter(mRvAdapter);
    }

    @Override
    public void updateCategoryMap(List dataList) {

        for (UnreadCountsEntity entity : (List<UnreadCountsEntity>) dataList) {

            if (entity.id.startsWith("user/") && entity.id.endsWith("global.all")) {
                mHashMap.get("All").count = entity.count;
                mHashMap.get("All").updated = entity.updated;
            } else if (entity.id.startsWith("user/") && entity.id.contains("/category/")) {
                mHashMap.get(entity.id).count = entity.count;
                mHashMap.get(entity.id).updated = entity.updated;

                for (Feed feed : mHashMap.get(entity.id).feedList) {
                    feed.count = entity.count;
                    feed.updated = entity.updated;
                }

            }

        }

        mRvAdapter.notifyDataSetChanged();
    }

    @Override
    protected TabListPresenter createPresenter() {
        return new TabListPresenter();
    }
}
