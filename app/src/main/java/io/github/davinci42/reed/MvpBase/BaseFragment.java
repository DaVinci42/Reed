package io.github.davinci42.reed.MvpBase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V, P>
	implements BaseView {

	private View mView;

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		initData();
		initView();
		updateData();
		updateView();
	}

	@Nullable @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(getLayoutResId(), container, false);
		}
		ButterKnife.bind(this, mView);
		return mView;
	}

	public abstract int getLayoutResId();

	public abstract void initView();

	public abstract void initData();

	public abstract void updateView();

	public abstract void updateData();

	@Override public void showError(Context context, String error) {
		Snackbar.make(((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content), error,
			Snackbar.LENGTH_LONG);
	}
}

