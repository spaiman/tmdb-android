package com.setiawanpaiman.tmdb.android.data.source;

import android.support.annotation.NonNull;

import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

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

    @Inject
    public MovieRepository(@NonNull @Remote MovieDataSource movieRemoteDataSource) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }

    @Override
    public Observable<List<MovieViewModel>> getTopRatedMovies() {
        return mMovieRemoteDataSource.getTopRatedMovies();
    }
}
