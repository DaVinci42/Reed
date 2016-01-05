package io.github.davinci42.reed.MvpBase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends Fragment implements MvpView {

	private P mPresenter;

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (mPresenter == null) {
			mPresenter = createPresenter();
		}

		if (mPresenter == null) {
			throw new NullPointerException("Presenter must not be null in " + this.getClass().getName());
		}

		mPresenter.attachView((V) this);
	}

	@Override public void onDestroyView() {
		super.onDestroyView();
		// TODO 此处容易产生bug，不用用setRetainInstance(true)

		if (mPresenter == null) {
			mPresenter = createPresenter();
		}
		mPresenter.detachView(getRetainInstance());
	}

	protected abstract P createPresenter();

	public P getPresenter() {
		return mPresenter;
	}
}
