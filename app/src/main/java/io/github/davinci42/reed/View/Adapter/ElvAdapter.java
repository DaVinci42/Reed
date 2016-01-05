package io.github.davinci42.reed.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import io.github.davinci42.reed.Model.LocalBean.ElvCategory;
import io.github.davinci42.reed.Model.LocalBean.ElvFeed;
import io.github.davinci42.reed.Model.LocalBean.ReedLocalData;
import io.github.davinci42.reed.R;
import io.github.davinci42.reed.View.Activity.FeedRvActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class ElvAdapter extends BaseExpandableListAdapter {

	private List<ElvCategory> mCategoryList;
	private Context mContext;

	public ElvAdapter(Context context, Map<String, ElvCategory> categoryMap) {

		setData(context, categoryMap);
	}

	@Override public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
		ViewGroup parent) {

		ElvCategory elvCategory = mCategoryList.get(groupPosition);

		final ElvFeed feedItem = elvCategory.elvFeeds.get(childPosition);

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_elv_feed, parent, false);
		}

		TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);

		tvTitle.setText(feedItem.title);
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				navigateToFeedRv(feedItem);
			}
		});

		return convertView;
	}

	@Override public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	@Override public int getGroupCount() {
		return mCategoryList.size();
	}

	@Override public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ElvCategory elvCategory = mCategoryList.get(groupPosition);

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_elv_category, parent, false);
		}

		TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);

		tvTitle.setText(elvCategory.label);

		return convertView;
	}

	@Override public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override public boolean hasStableIds() {
		return false;
	}

	@Override public Object getChild(int groupPosition, int childPosition) {
		return mCategoryList.get(groupPosition).elvFeeds.get(childPosition);
	}

	@Override public Object getGroup(int groupPosition) {
		return mCategoryList.get(groupPosition);
	}

	@Override public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override public int getChildrenCount(int groupPosition) {
		return mCategoryList.get(groupPosition).elvFeeds.size();
	}

	private void navigateToFeedRv(ElvFeed elvFeed) {
		Intent intent = new Intent(mContext, FeedRvActivity.class);
		intent.putExtra(ReedLocalData.FEED_KEY, elvFeed);
		mContext.startActivity(intent);
	}

	public void setData(Context context, Map<String, ElvCategory> categoryMap) {
		mContext = context;
		mCategoryList = new ArrayList<>();

		for (ElvCategory category : categoryMap.values()) {

			ElvCategory elvCategory = new ElvCategory();
			elvCategory.label = category.label;
			elvCategory.elvFeeds = new ArrayList<>();

			for (ElvFeed feed : categoryMap.get(category.label).feedMap.values()) {
				elvCategory.elvFeeds.add(feed);
			}

			mCategoryList.add(elvCategory);
		}
	}
}
