package io.github.davinci42.seed.View.Activity;

import android.os.Bundle;

import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.MvpBase.MvpActivity;
import io.github.davinci42.seed.Presenter.RvPresenter;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.View.ViewInterface.RvView;

public class RvActivity extends MvpActivity<RvView, RvPresenter> implements RvView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        TabListItem item = (TabListItem) getIntent().getSerializableExtra(FeedlyData.TABLISTITEM_KEY);
        getPresenter().getItemEntryList(item);
    }

    @Override
    protected RvPresenter createPresenter() {
        return new RvPresenter();
    }
}
