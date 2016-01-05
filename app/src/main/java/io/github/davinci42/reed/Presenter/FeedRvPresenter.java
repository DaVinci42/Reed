package io.github.davinci42.reed.Presenter;

import android.util.Log;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.Model.ReedNetUtils.ReedNetwork;
import io.github.davinci42.reed.Model.Utils.ReedCallback;
import io.github.davinci42.reed.MvpBase.BasePresenter;
import io.github.davinci42.reed.View.ViewInterface.FeedRvView;
import java.util.List;

/**
 * Created by davinci42 on 2016/1/4.
 */
public class FeedRvPresenter extends BasePresenter<FeedRvView> {
	ReedNetwork mReedNetwork = new ReedNetwork();

	public void getEntryListWithIds(final List<String> ids) {

		mReedNetwork.getEntryListWithId(ids, new ReedCallback<Entry>() {
			@Override public void onSuccess(List<Entry> feedlyDataList) {
				getView().refreshEntriesFromNet(feedlyDataList);
				Log.e("davinci42", "entries got");
			}

			@Override public void onException(String error) {
				Log.e("davinci42", "FeedRvPresenter " + error);
			}
		});
	}
}
