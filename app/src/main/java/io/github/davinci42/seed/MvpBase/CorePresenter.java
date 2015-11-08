package io.github.davinci42.seed.MvpBase;

/**
 * Created by davinci42 on 15/10/27.
 */
public interface CorePresenter<V extends CoreView> {

    void attachView(V view);

    void detachView(boolean retainInstance);
}
