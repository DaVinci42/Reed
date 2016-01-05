package io.github.davinci42.reed.View.Fragment;

import io.github.davinci42.reed.Model.LocalBean.ElvCategory;
import io.github.davinci42.reed.Model.NetBean.Entry;
import java.util.List;
import java.util.Map;

public class SavedFragment extends TabBaseFragment {

	public SavedFragment() {
	}

	@Override public void updateDataFromNet(List<Entry> entries) {
		getPresenter().updateDataFromEntryList(entries);
	}

	@Override public void refreshCategoryMap(Map<String, ElvCategory> categoryMap) {
		updateCategoryMap(categoryMap);
	}
}
