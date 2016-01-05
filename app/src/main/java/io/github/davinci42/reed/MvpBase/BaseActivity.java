package io.github.davinci42.reed.MvpBase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import butterknife.ButterKnife;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends MvpActivity<V, P>
	implements BaseView {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResId());

		ButterKnife.bind(this);
		initData();
		initView();
		updateData();
		updateView();
	}

	public abstract int getLayoutResId();

	public abstract void initData();

	public abstract void updateView();

	public abstract void initView();

	public abstract void updateData();

	@Override public void showError(Context context, String error) {
		Snackbar.make(((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content), error,
			Snackbar.LENGTH_LONG);
	}
}
