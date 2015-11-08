package io.github.davinci.seed.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.davinci.seed.Model.Entity.FeedlyData;
import io.github.davinci.seed.Model.Entity.TabListItem;
import io.github.davinci.seed.MvpBase.MvpActivity;
import io.github.davinci.seed.Presenter.RvPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.ViewInterface.RvView;

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
