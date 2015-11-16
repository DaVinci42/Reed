package io.github.davinci42.seed.Model.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ying on 10/18/15.
 */
public interface SeedCallback<T extends Serializable> extends Serializable{
    void onSuccess(List<T> feedlyDataList);
    void onException(Exception e);
}
