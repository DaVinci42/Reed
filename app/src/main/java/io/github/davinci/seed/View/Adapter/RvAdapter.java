package io.github.davinci.seed.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import io.github.davinci.seed.Model.Entity.CategoryWithFeeds;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.R;

/**
 * Created by davinci42 on 15/10/22.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private HashMap<String, CategoryWithFeeds> mHashMap;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mFeedIcon;
        TextView mFeedTitle;
        TextView mUnreadCount;

        public ViewHolder(View itemView) {
            super(itemView);
            mFeedIcon = (ImageView) itemView.findViewById(R.id.iv_feed_icon);
            mFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
            mUnreadCount = (TextView) itemView.findViewById(R.id.tv_unread_count);
        }
    }

    public RvAdapter(HashMap<String, CategoryWithFeeds> feedList) {
        mHashMap = feedList;
    }


    @Override
    public RvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        // 最上方添加全部
        return mHashMap.size() + 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
}
