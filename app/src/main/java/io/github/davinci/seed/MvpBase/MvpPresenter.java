package io.github.davinci.seed.MvpBase;

/**
 * Created by davinci42 on 15/11/3.
 */
public class MvpPresenter<V extends MvpView> implements CorePresenter<V> {

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
