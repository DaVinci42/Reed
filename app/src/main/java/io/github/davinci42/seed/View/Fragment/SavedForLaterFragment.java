package io.github.davinci42.seed.View.Fragment;

import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Database.SavedEntryDbHelper;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.View.Adapter.TabElvAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedForLaterFragment extends TabListFragment {

	@Bind(R.id.elv) ExpandableListView mElv;
	@Bind(R.id.tv_empty_hint) TextView mTvEmptyHint;

	private Map<String, List<Entry>> mCategoryMap = new HashMap<>();
	private List<String> mCategoryList = new ArrayList<>();
	private List<List<Entry>> mEntryList = new ArrayList<>();
	private TabElvAdapter mTabElvAdapter;

	@Override public void getEntryListFromDb() {
		getPresenter().updateEntryList(getContext(), new SavedEntryDbHelper(getContext()));
	}

	@Override public Map<String, List<Entry>> getCategoryMap() {
		return mCategoryMap;
	}

	@Override public List<String> getCategoryList() {
		return mCategoryList;
	}

	@Override public List<List<Entry>> getEntryList() {
		return mEntryList;
	}

	@Override public TabElvAdapter getTabElvAdapter() {
		return mTabElvAdapter;
	}

	@Override public void setTabElvAdapter(TabElvAdapter adapter) {
		this.mTabElvAdapter = adapter;
	}

	@Override public ExpandableListView getElv() {
		return mElv;
	}

	@Override public TextView getEmptyView() {
		return mTvEmptyHint;
	}
}
