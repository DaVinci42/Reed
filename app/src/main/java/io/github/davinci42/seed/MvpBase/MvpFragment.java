package io.github.davinci42.seed.MvpBase;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.davinci42.seed.Model.Entity.FeedlyData;
import io.github.davinci42.seed.Model.Entity.TabListItem;
import io.github.davinci42.seed.View.Activity.RvActivity;

public abstract class MvpFragment<V extends CoreView, P extends CorePresenter<V>> extends CoreFragment<V, P> implements MvpView{

    private View mView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutResId(), container, false);
        }
        return mView;
    }

    public abstract int getLayoutResId();

    public void navigateToRvActivity(TabListItem item) {
        Intent intent = new Intent(getActivity(), RvActivity.class);
        intent.putExtra(FeedlyData.TABLISTITEM_KEY, item);
        startActivity(intent);
    }
}

