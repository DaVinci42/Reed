package io.github.davinci42.seed.MvpBase;


import android.os.Bundle;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends CoreActivity<V, P> implements MvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        initData();
        updateView();
    }

    public abstract int getLayoutResId();

    public abstract void initData();

    public abstract void updateView();
}
