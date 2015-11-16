package io.github.davinci42.seed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davinci42 on 15/11/16.
 */
public class EntryDbHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "entry.db";


    public EntryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EntryDbSchema.EntryTable.NAME + "(" +
                        EntryDbSchema.Cols.ID + ", " +
                        EntryDbSchema.Cols.TITLE + ", " +
                        EntryDbSchema.Cols.AUTHOR + ", " +
                        EntryDbSchema.Cols.UPDATED + ", " +
                        EntryDbSchema.Cols.FEEDID + ", " +
                        EntryDbSchema.Cols.CATEGORYID + ", " +
                        EntryDbSchema.Cols.CATEGORYLABEL + ", " +
                        EntryDbSchema.Cols.CONTENT + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
