package io.github.davinci.seed.Model.Entity;

import java.util.List;

/**
 * Created by davinci42 on 15/10/22.
 */
public class Feed extends FeedlyData {

    /**
     * sponsored : false
     * website : http://www.engadget.com/
     * velocity : 180.3
     * subscribers : 123
     * language : en
     * featured : false
     * title : Engadget
     * curated : false
     * state : alive
     * topics : ["tech","gadgets"]
     * id : feed/http://feeds.engadget.com/weblogsinc/engadget
     */

    public boolean sponsored;
    public String website;
    public double velocity;
    public int subscribers;
    public String language;
    public boolean featured;
    public String title;
    public boolean curated;
    public String state;
    public String id;
    public List<String> topics;
    /**
     * feedId : feed/http://news.dbanotes.net/rss
     * description : Startup News of China
     * contentType : article
     * iconUrl : http://storage.googleapis.com/site-assets/KpnMfcl7vkIekRlZ8uJ2AOlhJcdIiA_M3b0i2aYSAUg_sicon-1507b0d6daf
     * partial : true
     * visualUrl : http://storage.googleapis.com/site-assets/KpnMfcl7vkIekRlZ8uJ2AOlhJcdIiA_M3b0i2aYSAUg_svisual-1508c6f636f
     */

    public String feedId;
    public String description;
    public String contentType;
    public String iconUrl;
    public boolean partial;
    public String visualUrl;

    public int count;
    public long updated;
}
