package io.github.davinci42.reed.MvpBase;

import android.content.Context;

/**
 * Created by davinci42 on 15/11/3.
 */
public interface BaseView extends MvpView {

	void showError(Context context, String error);
}
