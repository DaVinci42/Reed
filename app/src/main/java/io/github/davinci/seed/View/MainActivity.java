package io.github.davinci.seed.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import io.github.davinci.seed.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_main_activity);
    }


}
