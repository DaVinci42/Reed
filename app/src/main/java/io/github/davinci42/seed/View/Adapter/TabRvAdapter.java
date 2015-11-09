package io.github.davinci42.seed.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.View.Interface.OnListClickListener;

/**
 * Created by davinci42 on 15/10/22.
 */
public class TabRvAdapter extends RecyclerView.Adapter<TabRvAdapter.ViewHolder> {

    private List<TabListItem> mTabItemList;
    private OnListClickListener mOnFeedListClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mRlRootLayout;
        ImageView mIvFeedIcon;
        TextView mTvFeedTitle;
        TextView mTvUnreadCount;

        public ViewHolder(View itemView) {
            super(itemView);
            mRlRootLayout = (RelativeLayout) itemView.findViewById(R.id.root_layout);
            mIvFeedIcon = (ImageView) itemView.findViewById(R.id.iv_feed_icon);
            mTvFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
            mTvUnreadCount = (TextView) itemView.findViewById(R.id.tv_unread_count);
        }
    }

    public TabRvAdapter(List<TabListItem> tabListItemList, OnListClickListener onFeedListClickListener) {
        mTabItemList = tabListItemList;
        mOnFeedListClickListener = onFeedListClickListener;
    }


    @Override
    public TabRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return mTabItemList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mTabItemList.get(position).count == 0) {
            holder.mRlRootLayout.setVisibility(View.GONE);
        } else {
            holder.mRlRootLayout.setVisibility(View.VISIBLE);
        }

        holder.mTvFeedTitle.setText(mTabItemList.get(position).title);
        holder.mTvUnreadCount.setText(String.valueOf(mTabItemList.get(position).count));

        holder.mRlRootLayout.setTag(mTabItemList.get(position));
        holder.mRlRootLayout.setOnClickListener(mOnFeedListClickListener);

    }
}
