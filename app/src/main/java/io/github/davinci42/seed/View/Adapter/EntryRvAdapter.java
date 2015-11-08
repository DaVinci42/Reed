package io.github.davinci42.seed.View.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.View.Interface.OnListClickListener;

/**
 * Created by ying on 11/8/15.
 */
public class EntryRvAdapter extends RecyclerView.Adapter<EntryRvAdapter.ViewHolder> {

    private List<Entry> mEntryList;
    private OnListClickListener mOnListClickListener;

    public EntryRvAdapter(List<Entry> entries, OnListClickListener onListClickListener) {
        mEntryList = entries;
        mOnListClickListener = onListClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout mLlRootLayout;
        TextView mTvTime;
        TextView mTvTitle;
        TextView mTvSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            mLlRootLayout = (LinearLayout) itemView.findViewById(R.id.root_layout);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvSummary = (TextView) itemView.findViewById(R.id.tv_summary);
        }
    }


    public EntryRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entry, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return mEntryList.size();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvTitle.setText(mEntryList.get(position).title);

        holder.mLlRootLayout.setTag(mEntryList.get(position));
        holder.mLlRootLayout.setOnClickListener(mOnListClickListener);
    }
}
