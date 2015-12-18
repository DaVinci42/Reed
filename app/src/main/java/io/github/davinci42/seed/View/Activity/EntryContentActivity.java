package io.github.davinci42.seed.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci.seed.R;
import io.github.davinci42.seed.Model.Entity.Entry;
import io.github.davinci42.seed.Model.Entity.FeedlyData;

public class EntryContentActivity extends AppCompatActivity {

	@Bind(R.id.tv_title) TextView mTvTitle;
	@Bind(R.id.tv_author) TextView mTvAuthor;
	@Bind(R.id.wv_content) WebView mWvContent;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_entry_content);
		ButterKnife.bind(this);
		initContentView();
	}

	private void initContentView() {

		Entry mEntry = (Entry) getIntent().getSerializableExtra(FeedlyData.ENTRY_KEY);

		mTvTitle.setText(mEntry.title);
		mTvAuthor.setText(mEntry.author);
		mWvContent.getSettings().setDefaultTextEncodingName("utf-8");

		if (mEntry.content != null && mEntry.content.content != null) {
			mWvContent.loadData(mEntry.content.content, "text/html; charset=utf-8", "utf-8");
		} else if (mEntry.summary != null && mEntry.summary.content != null) {
			mWvContent.loadData(mEntry.summary.content, "text/html; charset=utf-8", "utf-8");
		}
	}
}
