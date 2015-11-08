package io.github.davinci.seed.Model.Entity;

import java.io.Serializable;

import io.github.davinci.seed.Model.FeedlyNetUtils.SignHelper;

/**
 * Created by ying on 10/18/15.
 */
public class FeedlyData implements Serializable {

    public static final String ALL_CATEGORY_ID = "user/" + SignHelper.getUserId() + "/category/global.all";
    public static final String ALL_CATEGORY_LABEL = "All";
    public static final String UNCATEGORIZED_ID = "user/" + SignHelper.getUserId() + "/category/global.uncategorized";
    public static final String UNCATEGORIZED_LABEL = "Uncategorized";
    public static final String FEED_MAP = "FeedMap";
    public static final String TABLISTITEM_KEY = "TabListItemKey";
}
