package com.setiawanpaiman.tmdb.android.data.source;

import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public interface MovieDataSource {

    Observable<List<MovieViewModel>> getPopularMovies();

    Observable<List<MovieViewModel>> getTopRatedMovies();

    Observable<List<VideoViewModel>> getTrailers(final long movieId);

    Observable<List<ReviewViewModel>> getReviews(final long movieId);
}
