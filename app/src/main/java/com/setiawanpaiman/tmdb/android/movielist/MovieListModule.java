package com.setiawanpaiman.tmdb.android.movielist;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Setiawan Paiman on 25/6/17.
 */
@Module
public class MovieListModule {

    private final MovieListContract.View mView;

    public MovieListModule(MovieListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MovieListContract.View providesView() {
        return mView;
    }
}
