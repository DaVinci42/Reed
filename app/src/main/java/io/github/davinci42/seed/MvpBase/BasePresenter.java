package io.github.davinci42.seed.MvpBase;

/**
 * Created by davinci42 on 15/11/3.
 */
public class BasePresenter<V extends BaseView> implements MvpPresenter<V> {

	private V mView;

	@Override public void attachView(V view) {
		mView = view;
	}

	@Override public void detachView(boolean retainInstance) {
		if (!retainInstance) {
			mView = null;
		}
	}

	public V getView() {
		return mView;
	}
}
