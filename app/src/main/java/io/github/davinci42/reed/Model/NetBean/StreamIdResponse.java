package io.github.davinci42.reed.Model.NetBean;

import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class StreamIdResponse extends ReedNetData {

	private static final long serialVersionUID = 2406182102982769455L;
	/**
	 * continuation : 13fb9d1263d:2a8ef5:db3da1a7
	 * ids : ["gRtwnDeqCDpZ42bXE9Sp7dNhm4R6NsipqFVbXn2XpDA=_13fb9d6f274:2ac9c5:f5718180","9bVktswTBLT3zSr0Oy09Gz8mJYLymYp71eEVeQryp2U=_13fb9d1263d:2a8ef5:db3da1a7"]
	 */

	public String continuation;
	public List<String> ids;
}
