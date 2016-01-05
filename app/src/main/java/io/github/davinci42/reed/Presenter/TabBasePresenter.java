package io.github.davinci42.reed.Presenter;

import io.github.davinci42.reed.Model.LocalBean.ElvCategory;
import io.github.davinci42.reed.Model.LocalBean.ElvFeed;
import io.github.davinci42.reed.Model.LocalBean.ReedLocalData;
import io.github.davinci42.reed.Model.NetBean.Category;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.MvpBase.BasePresenter;
import io.github.davinci42.reed.View.ViewInterface.TabBaseView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class TabBasePresenter extends BasePresenter<TabBaseView> {

	public void updateDataFromEntryList(List<Entry> entryList) {

		Map<String, ElvCategory> categoryMap = new HashMap<>();

		for (Entry entry : entryList) {
			if (entry.categories != null && !entry.categories.isEmpty()) {
				for (Category category : entry.categories) {
					if (categoryMap.keySet().contains(category.label)) {
						if (categoryMap.get(category.label).feedMap.containsKey(entry.origin.title)) {
							categoryMap.get(category.label).feedMap.get(entry.origin.title).entryIds.add(entry.id);
						} else {
							ElvFeed elvFeed = new ElvFeed();
							elvFeed.title = entry.origin.title;
							elvFeed.id = entry.origin.streamId;
							elvFeed.entryIds = new ArrayList<>();
							elvFeed.entryIds.add(entry.id);
							elvFeed.categories += category + ReedLocalData.CATEGORY_SPLIT;

							categoryMap.get(category.label).feedMap.put(elvFeed.title, elvFeed);
						}
					} else {
						ElvCategory elvCategory = new ElvCategory();
						elvCategory.label = category.label;
						elvCategory.feedMap = new HashMap<>();

						ElvFeed elvFeed = new ElvFeed();
						elvFeed.title = entry.origin.title;
						elvFeed.id = entry.origin.streamId;
						elvFeed.entryIds = new ArrayList<>();
						elvFeed.entryIds.add(entry.id);
						elvFeed.categories += category + ReedLocalData.CATEGORY_SPLIT;

						elvCategory.feedMap.put(elvFeed.title, elvFeed);

						categoryMap.put(category.label, elvCategory);
					}
				}
			}
		}

		getView().refreshCategoryMap(categoryMap);
	}
}
