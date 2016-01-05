package io.github.davinci42.reed.Model.Utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ying on 10/18/15.
 */
public interface ReedCallback<T extends Serializable> extends Serializable{
    void onSuccess(List<T> feedlyDataList);
    void onException(String error);
}
