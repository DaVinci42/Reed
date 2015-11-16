package io.github.davinci42.seed.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.github.davinci42.seed.Model.Utils.SeedCallback;

/**
 * Created by davinci42 on 15/11/16.
 */
public class RefreshService extends IntentService {

    private static final String TAG = "RefreshService";
    private SeedCallback mSeedCallback;

    public RefreshService() {
        super(TAG);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RefreshService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mSeedCallback = (SeedCallback) intent.getExtras().get(TAG);
        Log.e("davinci42", TAG);
    }
}
