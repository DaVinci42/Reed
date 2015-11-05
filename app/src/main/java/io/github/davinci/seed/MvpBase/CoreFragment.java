package io.github.davinci.seed.MvpBase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by davinci42 on 15/11/3.
 */
public abstract class CoreFragment<V extends CoreView, P extends CorePresenter<V>> extends Fragment implements CoreView {

    private P mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }

        if (mPresenter == null) {
            throw new NullPointerException("Presenter must not be null in " + this.getClass().getName());
        }

        mPresenter.attachView((V) this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // TODO 此处容易产生bug，不用用setRetainInstance(true)
        mPresenter.detachView(getRetainInstance());
    }

    protected abstract P createPresenter();

    public P getPresenter() {
        return mPresenter;
    }


}
