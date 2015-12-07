package io.github.davinci42.seed.View.ViewInterface;

import java.util.List;

import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.MvpBase.BaseView;

/**
 * Created by ying on 11/8/15.
 */
public interface RvView extends BaseView {

    void updateEntryList(List<Entry> entryList);
}
