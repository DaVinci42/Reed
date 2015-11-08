package io.github.davinci42.seed.Model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davinci42 on 15/10/26.
 */
public class Subscription extends FeedlyData {

    /**
     * id : feed/http://feed.williamlong.info/
     * title : 月光博客
     * website : http://www.williamlong.info/
     * categories : [{"id":"user/56b10ecf-0d59-4193-90b9-31f9495d1ee3/category/Blog","label":"Blog"}]
     * updated : 1445608472322
     * velocity : 5.8
     * subscribers : 40928
     * topics : ["知名博客","it","it 数码","博客"]
     * contentType : article
     * iconUrl : http://storage.googleapis.com/site-assets/C_7-8O3R9RCS_8hlfvxw6ZlduaEIQNc9FW6NUD7CFJ8_sicon-14834fdc4af
     * partial : false
     * visualUrl : http://storage.googleapis.com/site-assets/C_7-8O3R9RCS_8hlfvxw6ZlduaEIQNc9FW6NUD7CFJ8_svisual
     */

    public String id;
    public String title;
    public String website;
    public long updated;
    public double velocity;
    public int subscribers;
    public String contentType;
    public String iconUrl;
    public boolean partial;
    public String visualUrl;
    public List<Category> categories = new ArrayList<>();
    public List<String> topics = new ArrayList<>();


}
