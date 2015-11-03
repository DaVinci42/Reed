package io.github.davinci.seed.MvpBase;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends CoreActivity<V, P> implements MvpView {

}
