package io.github.davinci42.reed.MvpBase;

/**
 * Created by davinci42 on 15/10/27.
 */
public interface MvpPresenter<V extends MvpView> {

	void attachView(V view);

	void detachView(boolean retainInstance);
}
