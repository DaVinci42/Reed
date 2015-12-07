package io.github.davinci42.seed.MvpBase;

import android.os.Bundle;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends MvpActivity<V, P>
	implements BaseView {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());

		initData();
		updateView();
	}

	public abstract int getLayoutResId();

	public abstract void initData();

	public abstract void updateView();
}
