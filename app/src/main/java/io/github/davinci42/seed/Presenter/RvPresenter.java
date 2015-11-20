package io.github.davinci42.seed.Presenter;


import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.Model.FeedlyNetUtils.FeedlyNetwork;
import io.github.davinci42.seed.MvpBase.MvpPresenter;
import io.github.davinci42.seed.View.ViewInterface.RvView;

/**
 * Created by ying on 11/8/15.
 */
public class RvPresenter extends MvpPresenter<RvView> {

    private FeedlyNetwork mFeedlyNetwork = new FeedlyNetwork();

    public void getItemEntryList(TabListItem item) {

    }

}
