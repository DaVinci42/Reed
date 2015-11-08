package io.github.davinci42.seed.Model.Entity;

import java.util.List;

/**
 * Created by davinci42 on 15/10/21.
 */
public class Entry extends FeedlyData {

    /**
     * published : 1367539068016
     * keywords : ["NBC","sci-fi"]
     * alternate : [{"href":"http://theverge.com/4236096","type":"text/html"}]
     * engagement : 18
     * tags : [{"id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/global.saved"},{"label":"inspiration","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/inspiration"}]
     * crawled : 1367539068016
     * updated : 1367539068016
     * content : {"direction":"ltr","content":"..."}
     * title : NBC's reviled sci-fi drama 'Heroes' may get a second lease on life as Xbox Live exclusive
     * engagementRate : 0.78
     * author : Nathan Ingraham
     * categories : [{"label":"tech","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/tech"}]
     * unread : true
     * canonical : [{"href":"http://www.theverge.com/2013/4/17/4236096/nbc-heroes-may-get-a-second-lease-on-life-on-xbox-live","type":"text/html"}]
     * origin : {"htmlUrl":"http://www.theverge.com/","title":"The Verge -  All Posts","streamId":"feed/http://www.theverge.com/rss/full.xml"}
     * id : gRtwnDeqCDpZ42bXE9Sp7dNhm4R6NsipqFVbXn2XpDA=_13fb9d6f274:2ac9c5:f5718180
     */

    public long published;
    public int engagement;
    public long crawled;
    public long updated;
    public ContentEntity content;
    public String title;
    public double engagementRate;
    public String author;
    public boolean unread;
//    public OriginEntity origin;
    public String id;
    public List<String> keywords;
//    public List<AlternateEntity> alternate;
//    public List<TagsEntity> tags;
    public List<Category> categories;
//    public List<CanonicalEntity> canonical;

    public static class ContentEntity {
        /**
         * direction : ltr
         * content : ...
         */

        public String direction;
        public String content;
    }
//
//    public static class OriginEntity {
//        /**
//         * htmlUrl : http://www.theverge.com/
//         * title : The Verge -  All Posts
//         * streamId : feed/http://www.theverge.com/rss/full.xml
//         */
//
//        public String htmlUrl;
//        public String title;
//        public String streamId;
//    }

//    public static class AlternateEntity {
//        /**
//         * href : http://theverge.com/4236096
//         * type : text/html
//         */
//
//        public String href;
//        public String type;
//    }

//    public static class TagsEntity {
//        /**
//         * id : user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/global.saved
//         */
//
//        public String id;
//    }


//    public static class CanonicalEntity {
//        /**
//         * href : http://www.theverge.com/2013/4/17/4236096/nbc-heroes-may-get-a-second-lease-on-life-on-xbox-live
//         * type : text/html
//         */
//
//        public String href;
//        public String type;
//    }
}
