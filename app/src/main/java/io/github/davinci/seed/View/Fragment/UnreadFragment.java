package io.github.davinci.seed.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.Model.Entity.Feed;
import io.github.davinci.seed.Presenter.UnreadPresenter;
import io.github.davinci.seed.R;
import io.github.davinci.seed.View.Adapter.TabRvAdapter;
import io.github.davinci.seed.View.ViewInterface.UnreadView;


public class UnreadFragment extends Fragment implements UnreadView{

    @Bind(R.id.rv)
    RecyclerView mRv;

    private List<Feed> mFeedList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ButterKnife.bind(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(mLayoutManager);
        TabRvAdapter mTabRvAdapter = new TabRvAdapter(mFeedList);
        mRv.setAdapter(mTabRvAdapter);

        return inflater.inflate(R.layout.widget_rv, container, false);
    }

    private UnreadPresenter getPresenter() {
        return new UnreadPresenter();
    }


}
