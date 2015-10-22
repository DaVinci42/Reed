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
import io.github.davinci.seed.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedForLaterFragment extends Fragment {

    @Bind(R.id.rv)
    RecyclerView mRv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ButterKnife.bind(getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());


        return inflater.inflate(R.layout.widget_rv, container, false);
    }

}
