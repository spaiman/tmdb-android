package com.setiawanpaiman.tmdb.android.data.source;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.setiawanpaiman.tmdb.android.content.MovieContract.MovieEntry;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */
@Singleton
public class MovieRepository implements MovieDataSource {

    @NonNull
    private final MovieDataSource mMovieRemoteDataSource;

    @NonNull
    private final ContentResolver mContentResolver;

    @Inject
    public MovieRepository(@NonNull @Remote MovieDataSource movieRemoteDataSource,
                           @NonNull ContentResolver contentResolver) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
        this.mContentResolver = contentResolver;
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }

    @Override
    public Observable<List<MovieViewModel>> getTopRatedMovies() {
        return mMovieRemoteDataSource.getTopRatedMovies();
    }

    @Override
    public Observable<List<MovieViewModel>> getFavoriteMovies() {
        List<MovieViewModel> results = new ArrayList<>();
        try {
            Cursor cursor = mContentResolver.query(MovieEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    final int id = cursor.getInt(cursor.getColumnIndex(MovieEntry._ID));
                    final String title = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_TITLE));
                    final String posterUrl = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_POSTER_URL));
                    final String plotSynopsis = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_PLOT_SYNPOSIS));
                    final double userRating = cursor.getDouble(cursor.getColumnIndex(MovieEntry.COLUMN_USER_RATING));
                    final String releaseDate = cursor.getString(cursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE));
                    results.add(new MovieViewModel(id, title, posterUrl, plotSynopsis, userRating, releaseDate));
                }
                cursor.close();
            }
        } catch (Exception ignore) {}
        return Observable.just(results);
    }

    @Override
    public Observable<List<VideoViewModel>> getTrailers(long movieId) {
        return mMovieRemoteDataSource.getTrailers(movieId);
    }

    @Override
    public Observable<List<ReviewViewModel>> getReviews(long movieId) {
        return mMovieRemoteDataSource.getReviews(movieId);
    }

    @Override
    public boolean isFavoriteMovie(MovieViewModel movieViewModel) {
        Cursor cursor = null;
        try {
            cursor = mContentResolver.query(
                    ContentUris.withAppendedId(MovieEntry.CONTENT_URI, movieViewModel.getId()),
                    null,
                    null,
                    null,
                    null);
            return cursor != null && cursor.getCount() > 0;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public boolean addFavoriteMovie(MovieViewModel movieViewModel) {
        ContentValues cv = new ContentValues();
        cv.put(MovieEntry._ID, movieViewModel.getId());
        cv.put(MovieEntry.COLUMN_TITLE, movieViewModel.getTitle());
        cv.put(MovieEntry.COLUMN_POSTER_URL, movieViewModel.getPosterUrl());
        cv.put(MovieEntry.COLUMN_PLOT_SYNPOSIS, movieViewModel.getPlotSynopsis());
        cv.put(MovieEntry.COLUMN_USER_RATING, movieViewModel.getUserRating());
        cv.put(MovieEntry.COLUMN_RELEASE_DATE, movieViewModel.getReleaseDate());

        Uri uri = mContentResolver.insert(MovieEntry.CONTENT_URI, cv);
        return uri != null;
    }

    @Override
    public boolean removeFavoriteMovie(MovieViewModel movieViewModel) {
        int deleted = mContentResolver.delete(
                ContentUris.withAppendedId(MovieEntry.CONTENT_URI, movieViewModel.getId()),
                null,
                null);
        return deleted > 0;
    }
}
