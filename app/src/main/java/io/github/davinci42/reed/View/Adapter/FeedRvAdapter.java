package io.github.davinci42.reed.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.github.davinci42.reed.Model.LocalBean.ReedLocalData;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.R;
import io.github.davinci42.reed.View.Activity.EntryContentActivity;
import java.util.Calendar;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class FeedRvAdapter extends RecyclerView.Adapter<FeedRvAdapter.ViewHolder> {

	private List<Entry> mEntries;
	private Context mContext;

	public FeedRvAdapter(Context context, List<Entry> entryList) {
		mContext = context;
		mEntries = entryList;
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView mTitle;
		TextView mTime;
		TextView mSummary;
		View mRoot;

		public ViewHolder(View itemView) {
			super(itemView);
			mRoot = (View) itemView.findViewById(R.id.ll_root);
			mTitle = (TextView) itemView.findViewById(R.id.tv_title);
			mTime = (TextView) itemView.findViewById(R.id.tv_time);
			mSummary = (TextView) itemView.findViewById(R.id.tv_summary);
		}
	}

	@Override public FeedRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_rv, parent, false);
		return new ViewHolder(v);
	}

	@Override public void onBindViewHolder(ViewHolder holder, int position) {
		final Entry entry = mEntries.get(position);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(entry.crawled);

		// 01-04 12:21
		String time =
			(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(
				Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);

		holder.mTime.setText(time);
		holder.mTitle.setText(entry.title);
		if (entry.content != null && entry.content.content != null) {
			holder.mSummary.setVisibility(View.VISIBLE);
			holder.mSummary.setText(Html.fromHtml(entry.content.content));
		} else {
			holder.mSummary.setVisibility(View.GONE);
		}

		holder.mRoot.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				navigateToEntry(entry);
			}
		});
	}

	@Override public int getItemCount() {
		return mEntries.size();
	}

	private void navigateToEntry(Entry entry) {
		Intent intent = new Intent(mContext, EntryContentActivity.class);
		intent.putExtra(ReedLocalData.ENTRY_KEY, entry);
		mContext.startActivity(intent);
	}
}
