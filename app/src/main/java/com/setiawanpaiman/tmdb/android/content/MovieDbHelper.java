package com.setiawanpaiman.tmdb.android.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.setiawanpaiman.tmdb.android.content.MovieContract.MovieEntry;

/**
 * Created by Setiawan Paiman on 30/7/17.
 */

public class MovieDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";

    private static final int VERSION = 1;

    MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE "  + MovieEntry.TABLE_NAME + " (" +
                MovieEntry._ID                  + " INTEGER PRIMARY KEY, " +
                MovieEntry.COLUMN_TITLE         + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POSTER_URL    + " TEXT, " +
                MovieEntry.COLUMN_PLOT_SYNPOSIS + " TEXT, " +
                MovieEntry.COLUMN_USER_RATING   + " NUMBER(2, 1), " +
                MovieEntry.COLUMN_RELEASE_DATE  + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
