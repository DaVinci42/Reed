package io.github.davinci42.seed.View.ViewInterface;

import io.github.davinci42.seed.MvpBase.BaseView;

/**
 * Created by davinci42 on 15/10/26.
 */
public interface MainView extends BaseView {
    void onUnreadDbUpdated();
    void onSavedDbUpdated();
    void onRecentlyDbUpdated();
}
