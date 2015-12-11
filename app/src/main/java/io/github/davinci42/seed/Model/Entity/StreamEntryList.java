package io.github.davinci42.seed.Model.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ying on 11/8/15.
 */
public class StreamEntryList extends FeedlyData {

    /**
     * ids : ["gRtwnDeqCDpZ42bXE9Sp7dNhm4R6NsipqFVbXn2XpDA=_13fb9d6f274:2ac9c5:f5718180","9bVktswTBLT3zSr0Oy09Gz8mJYLymYp71eEVeQryp2U=_13fb9d1263d:2a8ef5:db3da1a7"]
     * continuation : 13fb9d1263d:2a8ef5:db3da1a7
     */

    public String continuation;
    public List<String> ids = new ArrayList<>();
}
