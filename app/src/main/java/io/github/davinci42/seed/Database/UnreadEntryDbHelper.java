package io.github.davinci42.seed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davinci42 on 15/11/16.
 */
public class UnreadEntryDbHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "unread_entry.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + EntryDbSchema.EntryTable.NAME + "(" +
                EntryDbSchema.Cols.ID + ", " +
                EntryDbSchema.Cols.TITLE + ", " +
                EntryDbSchema.Cols.AUTHOR + ", " +
                EntryDbSchema.Cols.UPDATED + ", " +
                EntryDbSchema.Cols.FEEDID + ", " +
                EntryDbSchema.Cols.CONTENT + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EntryDbSchema.EntryTable.NAME;

    public UnreadEntryDbHelper(Context context) {
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
