package io.github.davinci.seed.Model.Entity;

import java.util.ArrayList;

/**
 * Created by davinci42 on 15/10/26.
 */
public class CategoryWithFeeds extends FeedlyData{

    public String label;
    public String id;
    public int count;
    public ArrayList<Feed> feedList = new ArrayList<>();
}
