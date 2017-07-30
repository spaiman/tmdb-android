package com.setiawanpaiman.tmdb.android.content;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Setiawan Paiman on 30/7/17.
 */

public class MovieContract {

    public static final String AUTHORITY = "com.setiawanpaiman.tmdb.android";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_URL = "poster_url";
        public static final String COLUMN_PLOT_SYNPOSIS = "plot_synopsis";
        public static final String COLUMN_USER_RATING = "user_rating";
        public static final String COLUMN_RELEASE_DATE = "release_date";
    }
}
