package com.setiawanpaiman.tmdb.android.moviedetail;

import com.setiawanpaiman.tmdb.android.BasePresenter;
import com.setiawanpaiman.tmdb.android.BaseView;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;

import java.util.List;

/**
 * Created by Setiawan Paiman on 1/7/17.
 */

public interface MovieDetailContract {

    interface View extends BaseView {
        void showTrailers(List<VideoViewModel> videoViewModels);

        void showReviews(List<ReviewViewModel> reviewViewModels);
    }

    interface Presenter extends BasePresenter {
        void loadTrailers();

        void loadReviews();

        boolean isFavoriteMovie(MovieViewModel movieViewModel);

        boolean addFavoriteMovie(MovieViewModel movieViewModel);

        boolean removeFavoriteMovie(MovieViewModel movieViewModel);
    }
}
