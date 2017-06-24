package com.setiawanpaiman.tmdb.android.data.model;

import android.support.annotation.NonNull;

import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public class Movie {

    private long id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;
    private String release_date;

    @NonNull
    public MovieViewModel toViewModel() {
        return new MovieViewModel(id, title, poster_path, overview, vote_average, release_date);
    }
}
