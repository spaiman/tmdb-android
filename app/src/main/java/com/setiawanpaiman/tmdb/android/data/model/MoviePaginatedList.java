package com.setiawanpaiman.tmdb.android.data.model;

import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public class MoviePaginatedList {

    private List<Movie> results;

    public List<MovieViewModel> toListViewModel() {
        List<MovieViewModel> listViewModel = new ArrayList<>();
        for (Movie movie : results) {
            listViewModel.add(movie.toViewModel());
        }
        return listViewModel;
    }
}
