package io.github.davinci42.reed.Model.NetBean;

import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class Subscription extends ReedNetData {

	private static final long serialVersionUID = 1075107582638495880L;
	/**
	 * visualUrl : http://pbs.twimg.com/profile_images/1765276661/DMLogoTM-carton-icon-facebook-twitter_bigger.jpg
	 * website : http://design-milk.com
	 * categories : [{"label":"design","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/design"},{"label":"must reads","id":"user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/global.must"}]
	 * added : 1367539068016
	 * updated : 1367539068016
	 * id : feed/http://feeds.feedburner.com/design-milk
	 * title : Design Milk
	 * sortid : 26152F8F
	 */

	public String visualUrl;
	public String website;
	public long added;
	public long updated;
	public String id;
	public String title;
	public String sortid;
	/**
	 * label : design
	 * id : user/c805fcbf-3acf-4302-a97e-d82f9d7c897f/category/design
	 */

	public List<CategoriesEntity> categories;

	public static class CategoriesEntity {
		public String label;
		public String id;
	}
}
