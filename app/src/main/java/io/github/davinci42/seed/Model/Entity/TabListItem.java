package io.github.davinci42.seed.Model.Entity;

/**
 * Created by davinci42 on 15/11/5.
 */
public class TabListItem extends FeedlyData {

    public String id;
    public String title;
    public int count;
    public long updated;
    public int iconUrl;

    public String type;

    public boolean ifFocus;

    public static final String TYPE_ALL = "All";
    public static final String TYPE_FEED = "Feed";
    public static final String TYPE_CATEGORY = "Category";



}
