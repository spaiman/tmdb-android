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
    private final MovieDataSource mMovieRemoteDataSouce;

    @Inject
    public MovieRepository(@NonNull @Remote MovieDataSource movieRemoteDataSouce) {
        this.mMovieRemoteDataSouce = movieRemoteDataSouce;
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieRemoteDataSouce.getPopularMovies();
    }

    @Override
    public Observable<List<MovieViewModel>> getTopRatedMovies() {
        return mMovieRemoteDataSouce.getTopRatedMovies();
    }
}
