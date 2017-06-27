package com.setiawanpaiman.tmdb.android.movielist;

import com.setiawanpaiman.tmdb.android.data.source.MovieRepository;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.util.scheduler.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Setiawan Paiman on 25/6/17.
 */

public class MovieListPresenter implements MovieListContract.Presenter {

    private final MovieListContract.View mMovieListView;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    public MovieListPresenter(MovieListContract.View movieListView,
                              MovieRepository movieRepository,
                              BaseSchedulerProvider schedulerProvider) {
        this.mMovieListView = movieListView;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;

        this.mComposite = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        loadPopularMovies();
    }

    @Override
    public void unsubscribe() {
        this.mComposite.clear();
    }

    @Override
    public void loadPopularMovies() {
        mComposite.add(mMovieRepository.getPopularMovies()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(this::processMovieList,
                        throwable -> mMovieListView.showError(),
                        () -> {}));
    }

    private void processMovieList(final List<MovieViewModel> movieViewModels) {
        mMovieListView.showMovieList(movieViewModels);
    }
}
