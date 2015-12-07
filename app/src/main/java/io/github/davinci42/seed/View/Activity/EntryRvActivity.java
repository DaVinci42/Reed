package io.github.davinci42.seed.View.Activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.MvpBase.BaseActivity;
import io.github.davinci42.seed.Presenter.RvPresenter;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.View.Adapter.EntryRvAdapter;
import io.github.davinci42.seed.View.Interface.OnListClickListener;
import io.github.davinci42.seed.View.ViewInterface.RvView;

public class EntryRvActivity extends BaseActivity<RvView, RvPresenter> implements RvView {

    private List<Entry> mEntryList = new ArrayList<>();
    private RecyclerView mRv;
    private EntryRvAdapter mRvAdapter;

    @Override
    public void initData() {
        TabListItem item = (TabListItem) getIntent().getSerializableExtra(FeedlyData.TABLISTITEM_KEY);
        getPresenter().getItemEntryList(item);
    }

    @Override
    public void updateView() {
        initRv();
    }

    @Override
    protected RvPresenter createPresenter() {
        return new RvPresenter();
    }

    @Override
    public void updateEntryList(List<Entry> entryList) {
        mEntryList.clear();
        mEntryList.addAll(entryList);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mRvAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRv() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRvAdapter = new EntryRvAdapter(mEntryList, mOnListClickListener);
        mRv.setAdapter(mRvAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);

    }

    public OnListClickListener mOnListClickListener  = new OnListClickListener() {
        @Override
        public void onClick(View v) {
            Entry entry = (Entry) v.getTag();
            Log.e("davinci42", "Entry: " + new Gson().toJson(entry));
            Intent intent = new Intent(EntryRvActivity.this, EntryContentActivity.class);
            intent.putExtra(FeedlyData.ENTRY_KEY, entry);
            startActivity(intent);
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.widget_rv;
    }
}
