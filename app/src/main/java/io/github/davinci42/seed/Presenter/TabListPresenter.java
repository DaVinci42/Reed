package io.github.davinci42.seed.Presenter;


import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.MvpBase.MvpPresenter;
import io.github.davinci42.seed.View.ViewInterface.TabListView;

/**
 * Created by davinci42 on 15/11/4.
 */
public class TabListPresenter extends MvpPresenter<TabListView> {

    private FeedlyNetwork mFeedlyNetwork = new FeedlyNetwork();

    public void getUnreadFeedList() {


    }
}
