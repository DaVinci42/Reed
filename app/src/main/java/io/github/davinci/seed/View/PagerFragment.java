package io.github.davinci.seed.View;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.davinci.seed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {


    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.widget_rv, container, false);

        return view;
    }


}
