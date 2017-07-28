package com.setiawanpaiman.tmdb.android.moviedetail;

import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Setiawan Paiman on 1/7/17.
 */
@Module
public class MovieDetailModule {

    private final long mMovieId;
    private final MovieDetailContract.View mView;

    public MovieDetailModule(MovieViewModel movieViewModel, MovieDetailContract.View view) {
        this.mMovieId = movieViewModel.getId();
        this.mView = view;
    }

    @Provides
    MovieDetailContract.View providesView() {
        return mView;
    }

    @Provides
    long providesMovieId() {
        return mMovieId;
    }
}
