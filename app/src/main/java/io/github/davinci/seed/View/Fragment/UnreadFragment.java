package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.TabListItem;
import io.github.davinci.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.RvAdapter;
import io.github.davinci.seed.View.Interface.OnFeedListClickListener;
import io.github.davinci.seed.View.ViewInterface.TabListView;


public class UnreadFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {

    @Bind(R.id.rv)
    RecyclerView mRv;

    private RvAdapter mRvAdapter;
    private List<TabListItem> mTabItemList = new ArrayList<>();

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
        mRvAdapter = new RvAdapter(mTabItemList);
        mRv.setAdapter(mRvAdapter);
    }

    @Override
    public void updateCategoryMap(List dataList) {

        HashMap<String, CategoryWithFeeds> mHashMap = new HashMap<>();

        for (UnreadCountsEntity entity : (List<UnreadCountsEntity>) dataList) {

            if (entity.id.startsWith("user/") && entity.id.endsWith("global.all")) {
                mHashMap.get("All").count = entity.count;
                mHashMap.get("All").updated = entity.updated;
                TabListItem tabListItem = new TabListItem();
                tabListItem.id = entity.id;
                tabListItem.count = entity.count;
                tabListItem.title = "All";
                tabListItem.updated = entity.updated;
                mTabItemList.add(tabListItem);

            } else if (entity.id.startsWith("user/") && entity.id.contains("/category/")) {
                mHashMap.get(entity.id).count = entity.count;
                mHashMap.get(entity.id).updated = entity.updated;

                for (Feed feed : mHashMap.get(entity.id).feedList) {
                    feed.count = entity.count;
                    feed.updated = entity.updated;
                }

                TabListItem tabListItem = new TabListItem();
                tabListItem.id = entity.id;
                tabListItem.count = entity.count;
                tabListItem.title = mHashMap.get(entity.id).label;
                tabListItem.updated = entity.updated;

                mTabItemList.add(tabListItem);

            }
        }

        mRvAdapter.notifyDataSetChanged();
    }


    public OnFeedListClickListener mOnFeedListClickListener = new OnFeedListClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected TabListPresenter createPresenter() {
        return new TabListPresenter();
    }
}
