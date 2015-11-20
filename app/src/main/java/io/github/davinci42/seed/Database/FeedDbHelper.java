package io.github.davinci42.seed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by davinci42 on 15/11/16.
 */
public class FeedDbHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "feed.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedDbSchema.FeedTable.NAME + "(" +
                    FeedDbSchema.Cols.ID + ", " +
                    FeedDbSchema.Cols.TITLE + ", " +
                    FeedDbSchema.Cols.CATEGORYId + ", " +
                    FeedDbSchema.Cols.CATEGORYLabel + ", " +
                    FeedDbSchema.Cols.ICONURL + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedDbSchema.FeedTable.NAME;


    public FeedDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }


}
