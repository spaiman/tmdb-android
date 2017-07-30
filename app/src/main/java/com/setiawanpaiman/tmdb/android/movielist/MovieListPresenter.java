package com.setiawanpaiman.tmdb.android.movielist;

import com.setiawanpaiman.tmdb.android.data.source.MovieRepository;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.util.scheduler.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Setiawan Paiman on 25/6/17.
 */

class MovieListPresenter implements MovieListContract.Presenter {

    private final MovieListContract.View mMovieListView;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    MovieListPresenter(MovieListContract.View movieListView,
                       MovieRepository movieRepository,
                       BaseSchedulerProvider schedulerProvider) {
        this.mMovieListView = movieListView;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;

        this.mComposite = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        // no-op
    }

    @Override
    public void unsubscribe() {
        this.mComposite.clear();
    }

    @Override
    public void loadMovies(final boolean refresh, final SortOrder sortOrder) {
        mComposite.add(getObservableBySortOrder(sortOrder)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(movieViewModels -> {
                    processMovieList(refresh, movieViewModels);
                }, throwable -> mMovieListView.showError(), () -> {}));
    }

    private Observable<List<MovieViewModel>> getObservableBySortOrder(SortOrder sortOrder) {
        switch (sortOrder) {
            case BY_TOP_RATED:
                return mMovieRepository.getTopRatedMovies();
            case BY_FAVORITES:
                return mMovieRepository.getFavoriteMovies();
            case BY_POPULARITY:
            default:
                return mMovieRepository.getPopularMovies();
        }
    }

    private void processMovieList(final boolean refresh,
                                  final List<MovieViewModel> movieViewModels) {
        if (refresh) {
            mMovieListView.clearMovieList();
        }
        mMovieListView.showMovieList(movieViewModels);
    }
}
