package io.github.davinci.seed.MvpBase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class MvpFragment<V extends CoreView, P extends CorePresenter<V>> extends CoreFragment<V, P> implements MvpView{


}

