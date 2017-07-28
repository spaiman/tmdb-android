package com.setiawanpaiman.tmdb.android.http.api;

import com.setiawanpaiman.tmdb.android.data.model.Movie;
import com.setiawanpaiman.tmdb.android.data.model.PaginatedList;
import com.setiawanpaiman.tmdb.android.data.model.Video;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public interface MovieApi {

    @GET("/3/movie/popular")
    Observable<PaginatedList<Movie, MovieViewModel>> getPopularMovies();

    @GET("/3/movie/top_rated")
    Observable<PaginatedList<Movie, MovieViewModel>> getTopRatedMovies();

    @GET("/3/movie/{movieId}/videos")
    Observable<PaginatedList<Video, VideoViewModel>> getTrailers(@Path("movieId") long movieId);
}
