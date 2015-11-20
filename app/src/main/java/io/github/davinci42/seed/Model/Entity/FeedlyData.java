package io.github.davinci42.seed.Model.Entity;

import java.io.Serializable;

import io.github.davinci42.seed.Model.FeedlyNetUtils.SignHelper;

/**
 * Created by ying on 10/18/15.
 */
public class FeedlyData implements Serializable {

    public static final String ALL_CATEGORY_ID = "user/" + SignHelper.getUserId() + "/category/global.all";
    public static final String RECENTLY_READ = "user/" + SignHelper.getUserId() + "/tag/global.read";
    public static final String SAVED_FOR_LATER = "user/" + SignHelper.getUserId() + "/tag/global.saved";
    public static final String ALL_CATEGORY_LABEL = "All";
    public static final String UNCATEGORIZED_ID = "user/" + SignHelper.getUserId() + "/category/global.uncategorized";
    public static final String UNCATEGORIZED_LABEL = "Uncategorized";
    public static final String FEED_MAP = "FeedMap";
    public static final String TABLISTITEM_KEY = "TabListItemKey";
    public static final String ENTRY_KEY = "EntryKey";
}
