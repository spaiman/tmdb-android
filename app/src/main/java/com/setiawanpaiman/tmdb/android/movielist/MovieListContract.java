package com.setiawanpaiman.tmdb.android.movielist;

import com.setiawanpaiman.tmdb.android.BasePresenter;
import com.setiawanpaiman.tmdb.android.BaseView;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

import java.util.List;

/**
 * Created by Setiawan Paiman on 25/6/17.
 */

public interface MovieListContract {

    interface View extends BaseView {
        void clearMovieList();

        void showMovieList(final List<MovieViewModel> movieViewModels);

        void showError();
    }

    interface Presenter extends BasePresenter {

        enum SortOrder {
            BY_POPULARITY,
            BY_TOP_RATED
        }

        void loadMovies(boolean refresh, SortOrder sortOrder);
    }
}
