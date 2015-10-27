package io.github.davinci.seed.MvpBase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import io.github.davinci.seed.R;

public abstract class MvpActivity<P extends MvpPresenter> extends FragmentActivity implements MvpView {

    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        super.onDestroy();
        presenter.detachView(false);

    }

    protected abstract P createPresenter();
}
