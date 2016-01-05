package io.github.davinci42.reed.Model.NetBean;

import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class Feed extends ReedNetData {

	private static final long serialVersionUID = 7290455850686500798L;
	/**
	 * state : alive
	 * velocity : 180.3
	 * website : http://www.engadget.com/
	 * topics : ["tech","gadgets"]
	 * subscribers : 123
	 * curated : false
	 * sponsored : false
	 * id : feed/http://feeds.engadget.com/weblogsinc/engadget
	 * featured : false
	 * title : Engadget
	 * language : en
	 */

	public String state;
	public double velocity;
	public String website;
	public int subscribers;
	public boolean curated;
	public boolean sponsored;
	public String id;
	public boolean featured;
	public String title;
	public String language;
	public List<String> topics;
}
