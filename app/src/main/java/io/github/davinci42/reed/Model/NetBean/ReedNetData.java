package io.github.davinci42.reed.Model.NetBean;

import io.github.davinci42.reed.Model.ReedNetUtils.SignHelper;
import java.io.Serializable;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class ReedNetData implements Serializable {
	private static final long serialVersionUID = 1071323830552001181L;

	public static final String GLOBAL_MUST = "user/" + SignHelper.getUserId() + "/category/global.must";

	public static final String GLOBAL_ALL = "user/" + SignHelper.getUserId() + "/category/global.all";

	public static final String UNCATEGORIZED = "user/" + SignHelper.getUserId() + "/category/global.uncategorized";

	public static final String GLOBAL_READ = "user/" + SignHelper.getUserId() + "/tag/global.read";

	public static final String GLOBAL_SAVED = "user/" + SignHelper.getUserId() + "/tag/global.saved";
}
