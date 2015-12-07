package io.github.davinci42.seed.MvpBase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity
	implements MvpView {

	protected P mPresenter;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter = createPresenter();
		mPresenter.attachView((V) this);
	}

	protected abstract P createPresenter();

	@Override protected void onDestroy() {
		super.onDestroy();
		mPresenter.detachView(false);
	}

	public P getPresenter() {
		return mPresenter;
	}
}
