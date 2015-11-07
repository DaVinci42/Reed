package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private HashMap<String, CategoryWithFeeds> mHashMap = new HashMap<>();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("davinci42", "onViewCreated");
        initRv();
    }

    private void initRv() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        mRvAdapter = new RvAdapter(mTabItemList, mOnFeedListClickListener);
        mRv.setAdapter(mRvAdapter);

        Log.e("davinci42", "getUnreadList");
        getPresenter().getUnreadFeedList();
    }

    @Override
    public void updateCategoryMap(List dataList) {


        mTabItemList.clear();

        for (UnreadCountsEntity entity : (List<UnreadCountsEntity>) dataList) {

            if (entity.id.startsWith("user/") && entity.id.endsWith("global.all")) {
                mHashMap.get("All").count = entity.count;
                mHashMap.get("All").updated = entity.updated;
                TabListItem tabListItem = new TabListItem();
                tabListItem.id = entity.id;
                tabListItem.count = entity.count;
                tabListItem.title = "All";
                tabListItem.updated = entity.updated;
                tabListItem.type = "all";
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
                tabListItem.type = "category";

                mTabItemList.add(tabListItem);
            }
        }

        mRvAdapter.notifyDataSetChanged();
        Log.e("davinci42", "mTabList size: " + mTabItemList.size());
    }


    public OnFeedListClickListener mOnFeedListClickListener = new OnFeedListClickListener() {
        @Override
        public void onClick(View v) {
            String itemType = (String) v.getTag();

            switch (itemType) {
                case "all":
                    break;
                case "category":
                    break;
                case "feed":
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
<<<<<<< HEAD
        return R.layout.view_fragment;
=======
        return R.layout.widget_rv;
>>>>>>> 0e1ccd5ca9b54efa410a908650c9c840afc9d883
    }
}
