package io.github.davinci.seed.MvpBase;

/**
 * Created by ying on 10/27/15.
 */
public class MvpPresenter<V extends MvpView> implements BasePresenter<V> {

    private V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance) {
            mView = null;
        }
    }

    public V getView() {
        return mView;
    }
}
