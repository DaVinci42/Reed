package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.MvpBase.MvpFragment;
import io.github.davinci.seed.Presenter.SavedForLaterPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.ViewInterface.SavedForLaterView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedForLaterFragment extends MvpFragment<SavedForLaterView, SavedForLaterPresenter> implements SavedForLaterView {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.widget_rv, container, false);
    }

    @Override
    protected SavedForLaterPresenter createPresenter() {
        return new SavedForLaterPresenter();
    }
}
