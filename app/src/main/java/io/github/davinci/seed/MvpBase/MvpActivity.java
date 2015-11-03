package io.github.davinci.seed.MvpBase;

import android.os.Bundle;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends CoreActivity<V, P> implements MvpView {

    protected P presenter;
    @Override  protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
        super.onDestroy();
        presenter.detachView(false);
    }

    protected abstract P createPresenter();
}
