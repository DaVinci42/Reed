package io.github.davinci.seed.MvpBase;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class CoreActivity<V extends CoreView, P extends CorePresenter<V>> extends FragmentActivity implements CoreView {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView(false);
    }

    public P getPresenter() {
        return mPresenter;
    }
}
