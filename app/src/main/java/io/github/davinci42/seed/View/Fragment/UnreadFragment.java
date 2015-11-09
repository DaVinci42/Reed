package io.github.davinci42.seed.View.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import io.github.davinci42.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci42.seed.Model.Entity.Feed;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.Model.Entity.UnreadCountsEntity;
import io.github.davinci42.seed.MvpBase.MvpFragment;
import io.github.davinci42.seed.Presenter.TabListPresenter;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.View.Adapter.TabRvAdapter;
import io.github.davinci42.seed.View.Interface.OnListClickListener;
import io.github.davinci42.seed.View.ViewInterface.TabListView;


public class UnreadFragment extends MvpFragment<TabListView, TabListPresenter> implements TabListView {


    private RecyclerView mRv;

    private TabRvAdapter mRvAdapter;
    private List<TabListItem> mTabItemList = new ArrayList<>();
    private HashMap<String, CategoryWithFeeds> mHashMap = new HashMap<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHashMap = (HashMap<String, CategoryWithFeeds>) this.getArguments().getSerializable(FeedlyData.FEED_MAP);

        initRv();
    }

    private void initRv() {
        mRv = (RecyclerView) getActivity().findViewById(R.id.rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mRvAdapter = new TabRvAdapter(mTabItemList, mOnFeedListClickListener);
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

                mHashMap.get(entity.id).count = entity.count;
                mHashMap.get(entity.id).updated = entity.updated;

                for (Feed feed : mHashMap.get(entity.id).feedList) {

                    for (UnreadCountsEntity countsEntity : (List<UnreadCountsEntity>) dataList) {
                        if (feed.id.equals(countsEntity.id)) {
                            feed.count = countsEntity.count;
                            feed.updated = countsEntity.updated;
                        }
                    }
                }

                TabListItem tabListItem = new TabListItem();
                tabListItem.id = entity.id;
                tabListItem.count = entity.count;
                tabListItem.title = mHashMap.get(entity.id).label;
                tabListItem.updated = entity.updated;
                tabListItem.type = TabListItem.TYPE_CATEGORY;
                tabListItem.ifFocus = false;

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


    public OnListClickListener mOnFeedListClickListener = new OnListClickListener() {
        @Override
        public void onClick(View v) {
            TabListItem item = (TabListItem) v.getTag();

            switch (item.type) {
                case TabListItem.TYPE_ALL:
                    navigateToRvActivity(item);

                    break;
                case TabListItem.TYPE_CATEGORY:

                    item.ifFocus = !item.ifFocus;
                    List<Feed> feedList = mHashMap.get(item.id).feedList;

                    if (item.ifFocus) {
                        for (Feed feed : feedList) {
                            TabListItem tabItem = new TabListItem();
                            tabItem.id = feed.id;
                            tabItem.title = feed.title;
                            tabItem.updated = feed.updated;
                            tabItem.count = feed.count;
                            tabItem.type = TabListItem.TYPE_FEED;

                            if (tabItem.count != 0) {
                                mTabItemList.add(mTabItemList.indexOf(item) + 1, tabItem);
                            }

                        }
                    } else {

                        // Try to handle ConcurrentModificationException

                        List<TabListItem> tempTabList = new ArrayList<>();
                        tempTabList.addAll(mTabItemList);
                        for (Feed feed : feedList) {

                            for (TabListItem tabListItem : mTabItemList) {

                                if (feed.id.equals(tabListItem.id)) {
                                    tempTabList.remove(tabListItem);
                                }
                            }
                        }
                        mTabItemList.clear();
                        mTabItemList.addAll(tempTabList);
                    }



                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRvAdapter.notifyDataSetChanged();
                        }
                    });

                    break;
                case TabListItem.TYPE_FEED:
                    navigateToRvActivity(item);
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
