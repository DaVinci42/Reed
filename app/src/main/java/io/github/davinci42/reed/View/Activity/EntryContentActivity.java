package io.github.davinci42.reed.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.davinci42.reed.Model.LocalBean.ReedLocalData;
import io.github.davinci42.reed.Model.NetBean.Entry;
import io.github.davinci42.reed.R;
import java.util.Calendar;

public class EntryContentActivity extends AppCompatActivity {

	@Bind(R.id.tv_title) TextView mTvTitle;
	@Bind(R.id.tv_time) TextView mTvTime;
	@Bind(R.id.tv_feed) TextView mTvFeed;
	@Bind(R.id.wv_content) WebView mWvContent;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_content);
		ButterKnife.bind(this);
		Entry entry = (Entry) getIntent().getSerializableExtra(ReedLocalData.ENTRY_KEY);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(entry.crawled);
		String time =
			(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(
				Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);

		mTvTime.setText(time);
		mTvTitle.setText(entry.title);
		mTvFeed.setText(entry.origin.title);

		mWvContent.getSettings().setDefaultTextEncodingName("utf-8");

		String imageCssStyle = "<style>img{display: block; height: auto; max-width: 100%; margin: auto}</style>";

		if (entry.content != null && entry.content.content != null) {
			mWvContent.loadData(imageCssStyle + entry.content.content, "text/html; charset=utf-8", "utf-8");
		}
	}
}
