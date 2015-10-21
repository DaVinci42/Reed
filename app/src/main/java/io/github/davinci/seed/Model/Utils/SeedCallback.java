package io.github.davinci.seed.Model.Utils;

import java.io.Serializable;
import java.util.List;

import io.github.davinci.seed.Model.Entity.FeedlyData;

/**
 * Created by ying on 10/18/15.
 */
public interface SeedCallback<T extends FeedlyData> {
    void onSuccess(List<T> feedlyDataList);
    void onException(Exception e);
}
