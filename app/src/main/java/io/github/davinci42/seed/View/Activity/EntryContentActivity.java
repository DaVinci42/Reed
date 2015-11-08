package io.github.davinci42.seed.View.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.FeedlyData;

public class EntryContentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_entry_content);

        initContentView();
    }


    private void initContentView() {

        Entry mEntry = (Entry) getIntent().getSerializableExtra(FeedlyData.ENTRY_KEY);
        TextView mTvContent = (TextView) findViewById(R.id.tv_content);
        TextView mTvTitle = (TextView) findViewById(R.id.tv_title);
        TextView mTvAuthor = (TextView) findViewById(R.id.tv_author);
        mTvTitle.setText(mEntry.title);
        mTvAuthor.setText(mEntry.author);
        Log.e("davinci42", "Entry: " + new Gson().toJson(mEntry));
        if (mEntry.content != null && mEntry.content.content != null) {
            mTvContent.setText(Html.fromHtml(mEntry.content.content));
        }
    }
}
