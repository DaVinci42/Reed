package io.github.davinci42.reed.Model.NetBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class Entry extends ReedNetData {

	private static final long serialVersionUID = -2739973978879640842L;
	/**
	 * streamId : feed/http://www.theverge.com/rss/full.xml
	 * htmlUrl : http://www.theverge.com/
	 * title : The Verge -  All Posts
	 */

	public OriginEntity origin;
	/**
	 * origin : {"streamId":"feed/http://www.theverge.com/rss/full.xml","htmlUrl":"http://www.theverge.com/","title":"The
	 * Verge -  All Posts"}
	 * published : 1367539068016
	 * tags : [{"id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/global.saved"},{"label":"inspiration","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/inspiration"}]
	 * unread : true
	 * categories : [{"label":"tech","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/tech"}]
	 * engagementRate : 0.78
	 * canonical : [{"href":"http://www.theverge.com/2013/4/17/4236096/nbc-heroes-may-get-a-second-lease-on-life-on-xbox-live","type":"text/html"}]
	 * crawled : 1367539068016
	 * keywords : ["NBC","sci-fi"]
	 * alternate : [{"href":"http://theverge.com/4236096","type":"text/html"}]
	 * content : {"direction":"ltr","content":"..."}
	 * updated : 1367539068016
	 * id : gRtwnDeqCDpZ42bXE9Sp7dNhm4R6NsipqFVbXn2XpDA=_13fb9d6f274:2ac9c5:f5718180
	 * engagement : 18
	 * title : NBC's reviled sci-fi drama 'Heroes' may get a second lease on life as Xbox Live exclusive
	 * author : Nathan Ingraham
	 */

	public long published;
	public boolean unread;
	public double engagementRate;
	public long crawled;
	/**
	 * direction : ltr
	 * content : ...
	 */

	public ContentEntity content;
	public long updated;
	public String id;
	public int engagement;
	public String title;
	public String author;
	/**
	 * id : user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/tag/global.saved
	 */

	public List<TagsEntity> tags;
	/**
	 * label : tech
	 * id : user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/tech
	 */

	public List<Category> categories;
	/**
	 * href : http://www.theverge.com/2013/4/17/4236096/nbc-heroes-may-get-a-second-lease-on-life-on-xbox-live
	 * type : text/html
	 */

	public List<CanonicalEntity> canonical;
	public List<String> keywords;
	/**
	 * href : http://theverge.com/4236096
	 * type : text/html
	 */

	public List<AlternateEntity> alternate;

	public static class OriginEntity implements Serializable {
		private static final long serialVersionUID = -4390009642522892083L;
		public String streamId;
		public String htmlUrl;
		public String title;
	}

	public static class ContentEntity implements Serializable {
		private static final long serialVersionUID = 8162880724100315550L;
		public String direction;
		public String content;
	}

	public static class TagsEntity implements Serializable {
		private static final long serialVersionUID = 3185689014414412528L;
		public String id;
	}

	public static class CategoriesEntity implements Serializable {
		private static final long serialVersionUID = -6983004696048076383L;
		public String label;
		public String id;
	}

	public static class CanonicalEntity implements Serializable {
		private static final long serialVersionUID = -6461897421004611438L;
		public String href;
		public String type;
	}

	public static class AlternateEntity implements Serializable {
		private static final long serialVersionUID = -8775750156805404872L;
		public String href;
		public String type;
	}
}
