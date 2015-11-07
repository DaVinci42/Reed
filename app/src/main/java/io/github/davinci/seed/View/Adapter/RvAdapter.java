package io.github.davinci.seed.View.Adapter;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import io.github.davinci.seed.Model.Entity.TabListItem;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Interface.OnFeedListClickListener;

/**
 * Created by davinci42 on 15/10/22.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<TabListItem> mTabItemList;
    private OnFeedListClickListener mOnFeedListClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout mRootLayout;
        ImageView mFeedIcon;
        TextView mFeedTitle;
        TextView mUnreadCount;

        public ViewHolder(View itemView) {
            super(itemView);
            mRootLayout = (RelativeLayout) itemView.findViewById(R.id.root_layout);
            mFeedIcon = (ImageView) itemView.findViewById(R.id.iv_feed_icon);
            mFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
            mUnreadCount = (TextView) itemView.findViewById(R.id.tv_unread_count);
        }
    }

    public RvAdapter(List<TabListItem> tabListItemList, OnFeedListClickListener onFeedListClickListener) {
        mTabItemList = tabListItemList;
        mOnFeedListClickListener = onFeedListClickListener;
    }


    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return mTabItemList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (mTabItemList.get(position).count == 0 && !mTabItemList.get(position).type.equals(TabListItem.TYPE_FEED)) {
            holder.mRootLayout.setVisibility(View.GONE);
        } else {
            holder.mRootLayout.setVisibility(View.VISIBLE);
        }

        holder.mFeedTitle.setText(mTabItemList.get(position).title);
        holder.mUnreadCount.setText(String.valueOf(mTabItemList.get(position).count));

        holder.mRootLayout.setTag(mTabItemList.get(position));
        holder.mRootLayout.setOnClickListener(mOnFeedListClickListener);

    }
}
