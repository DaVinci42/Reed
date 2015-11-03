package io.github.davinci.seed.MvpBase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class MvpFragment<P extends CorePresenter> extends Fragment implements MvpView{

    protected P presenter;

    @Override
    public void onViewCreated(View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Create the presenter if needed
        if(presenter == null){
            presenter = createPresenter();
        }
        presenter.attachView(this);
    }

    @Override public void onDestroyView(){
        super.onDestroyView();
        presenter.detachView(getRetainInstance());
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy(){}

}

