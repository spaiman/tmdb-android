package com.setiawanpaiman.tmdb.android.http.api;

import com.setiawanpaiman.tmdb.android.data.model.MoviePaginatedList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public interface MovieApi {

    @GET("/3/movie/popular")
    Observable<MoviePaginatedList> getPopularMovies();
}
