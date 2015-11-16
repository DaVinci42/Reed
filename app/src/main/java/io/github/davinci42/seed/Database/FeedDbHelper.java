package io.github.davinci42.seed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davinci42 on 15/11/16.
 */
public class FeedDbHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "feed.db";


    public FeedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FeedDbSchema.FeedTable.NAME + "(" +
            FeedDbSchema.Cols.ID + ", " +
            FeedDbSchema.Cols.TITLE + ", " +
            FeedDbSchema.Cols.CATEGORYId + ", " +
            FeedDbSchema.Cols.CATEGORYLabel + ", " +
            FeedDbSchema.Cols.ICONURL + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
