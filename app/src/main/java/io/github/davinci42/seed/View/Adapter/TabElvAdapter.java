package io.github.davinci42.seed.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.View.Activity.EntryContentActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davinci42 on 2015/12/11.
 */
public class TabElvAdapter extends BaseExpandableListAdapter {

	private Context mContext;
	private List<String> mCategoryList = new ArrayList<>();
	private List<List<Entry>> mEntryList = new ArrayList<>();

	public TabElvAdapter(Context context, List<String> strings, List<List<Entry>> entryList) {
		mContext = context;
		mCategoryList = strings;
		mEntryList = entryList;
	}

	@Override public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
		ViewGroup parent) {
		final Entry entry = mEntryList.get(groupPosition).get(childPosition);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
		}

		TextView tvTitle = (TextView) convertView.findViewById(android.R.id.text1);
		tvTitle.setText(entry.title);

		convertView.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				navigateToContentActivity(entry);
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
		String categoryTitle = mCategoryList.get(groupPosition);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		TextView tvCategoty = (TextView) convertView.findViewById(android.R.id.text1);
		tvCategoty.setText(categoryTitle);
		return convertView;
	}

	@Override public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override public boolean hasStableIds() {
		return false;
	}

	@Override public Object getChild(int groupPosition, int childPosition) {
		return mEntryList.get(groupPosition).get(childPosition);
	}

	@Override public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override public Object getGroup(int groupPosition) {
		return mCategoryList.get(groupPosition);
	}

	@Override public int getChildrenCount(int groupPosition) {
		return mEntryList.get(groupPosition).size();
	}

	private void navigateToContentActivity(Entry entry) {
		Intent intent = new Intent(mContext, EntryContentActivity.class);
		intent.putExtra(FeedlyData.ENTRY_KEY, entry);
		mContext.startActivity(intent);
	}
}
