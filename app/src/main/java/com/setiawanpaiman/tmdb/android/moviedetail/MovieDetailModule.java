package com.setiawanpaiman.tmdb.android.moviedetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Setiawan Paiman on 1/7/17.
 */
@Module
public class MovieDetailModule {

    private final MovieDetailContract.View mView;

    public MovieDetailModule(MovieDetailContract.View view) {
        this.mView = view;
    }

    @Provides
    MovieDetailContract.View providesView() {
        return mView;
    }
}
