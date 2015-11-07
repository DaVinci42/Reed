package io.github.davinci.seed.MvpBase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

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
}

