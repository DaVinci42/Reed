package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Model.Entity.FeedlyData;
import io.github.davinci.seed.Model.Entity.TabListItem;
import io.github.davinci.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.RvAdapter;
import io.github.davinci.seed.View.Interface.OnFeedListClickListener;
import io.github.davinci.seed.View.ViewInterface.TabListView;


public class UnreadFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {


    private RecyclerView mRv;

    private RvAdapter mRvAdapter;
    private List<TabListItem> mTabItemList = new ArrayList<>();
    private HashMap<String, CategoryWithFeeds> mHashMap = new HashMap<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHashMap = (HashMap<String, CategoryWithFeeds>) this.getArguments().getSerializable("FeedMap");

        initRv();
    }

    private void initRv() {
        mRv = (RecyclerView) getActivity().findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mRvAdapter = new RvAdapter(mTabItemList, mOnFeedListClickListener);
        mRv.setAdapter(mRvAdapter);

        Log.e("davinci42", "getUnreadList");
        getPresenter().getUnreadFeedList();
    }

    @Override
    public void updateCategoryMap(List dataList) {

        Log.e("davinci42", "updateCategoryMap");

        mTabItemList.clear();

        for (UnreadCountsEntity entity : (List<UnreadCountsEntity>) dataList) {

            if (entity.id.equals(FeedlyData.ALL_CATEGORY_ID)) {
                mHashMap.get(FeedlyData.ALL_CATEGORY_ID).count = entity.count;
                mHashMap.get(FeedlyData.ALL_CATEGORY_ID).updated = entity.updated;
                TabListItem tabListItem = new TabListItem();
                tabListItem.id = entity.id;
                tabListItem.count = entity.count;
                tabListItem.title = FeedlyData.ALL_CATEGORY_LABEL;
                tabListItem.updated = entity.updated;
                tabListItem.type = TabListItem.TYPE_ALL;
                mTabItemList.add(tabListItem);

            } else if (entity.id.startsWith("user/") && entity.id.contains("/category/")
                    && !entity.id.equals(FeedlyData.ALL_CATEGORY_ID)) {

                Log.e("davinci42", "category entity: " + entity.id);

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
                tabListItem.type = TabListItem.TYPE_CATEGORY;

                mTabItemList.add(tabListItem);
            }
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRvAdapter.notifyDataSetChanged();
            }
        });

    }


    public OnFeedListClickListener mOnFeedListClickListener = new OnFeedListClickListener() {
        @Override
        public void onClick(View v) {
            TabListItem item = (TabListItem) v.getTag();

            switch (item.type) {
                case TabListItem.TYPE_ALL:
                    break;
                case TabListItem.TYPE_CATEGORY:

                    List<Feed> feedList = mHashMap.get(item.id).feedList;

                    for (Feed feed : feedList) {
                        TabListItem tabItem = new TabListItem();
                        tabItem.id = feed.id;
                        tabItem.title = feed.title;
                        tabItem.updated = feed.updated;
                        tabItem.type = TabListItem.TYPE_FEED;
                        mTabItemList.add(mTabItemList.indexOf(item) + 1, tabItem);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRvAdapter.notifyDataSetChanged();
                        }
                    });

                    break;
                case TabListItem.TYPE_FEED:
                    break;
            }
        }
    };

    @Override
    protected TabListPresenter createPresenter() {
        return new TabListPresenter();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.widget_rv;
    }
}
