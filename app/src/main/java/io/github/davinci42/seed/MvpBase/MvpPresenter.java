package io.github.davinci42.seed.MvpBase;

/**
 * Created by davinci42 on 15/10/27.
 */
public interface MvpPresenter<V extends MvpView> {

	void attachView(V view);

	void detachView(boolean retainInstance);
}
