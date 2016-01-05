package io.github.davinci42.reed.Presenter;

import android.content.Context;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.Model.ReedNetUtils.ReedNetwork;
import io.github.davinci42.reed.Model.Utils.ReedCallback;
import io.github.davinci42.reed.MvpBase.BasePresenter;
import io.github.davinci42.reed.View.ViewInterface.MainView;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class MainPresenter extends BasePresenter<MainView> {

	private Context mContext;

	public MainPresenter(Context context) {
		mContext = context;
	}

	private ReedNetwork mReedNetwork = new ReedNetwork();

	public void getUnreadFromNet() {

		mReedNetwork.getUnreadEntryList(new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				getView().updateUnreadData(feedlyDataList);
			}

			@Override public void onException(String error) {
				getView().showError(mContext, error);
			}
		});
	}

	public void getRecentlyFromNet() {
		mReedNetwork.getRecentlyEntryList(new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				getView().updateRecentlyData(feedlyDataList);
			}

			@Override public void onException(String error) {
				getView().showError(mContext, error);
			}
		});
	}

	public void getSavedFromNet() {
		mReedNetwork.getSavedForLaterEntryList(new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				getView().updateSavedData(feedlyDataList);
			}

			@Override public void onException(String error) {
				getView().showError(mContext, error);
			}
		});
	}
}
