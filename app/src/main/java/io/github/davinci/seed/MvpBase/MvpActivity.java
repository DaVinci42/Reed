package io.github.davinci.seed.MvpBase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public abstract class MvpActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView{

    protected P presenter;
    @Override  protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView(this);
        super.onDestroy();
        presenter.detachView(false);
    }

    protected abstract P createPresenter();
}
