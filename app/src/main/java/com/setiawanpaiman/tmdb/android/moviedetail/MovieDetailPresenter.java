package com.setiawanpaiman.tmdb.android.moviedetail;

import com.setiawanpaiman.tmdb.android.data.source.MovieRepository;
import com.setiawanpaiman.tmdb.android.util.scheduler.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Setiawan Paiman on 1/7/17.
 */

class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private final long mMovieId;
    private final MovieDetailContract.View mMovieDetailView;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    MovieDetailPresenter(@NonNull long movieId,
                         MovieDetailContract.View movieDetailView,
                         MovieRepository movieRepository,
                         BaseSchedulerProvider schedulerProvider) {
        this.mMovieId = movieId;
        this.mMovieDetailView = movieDetailView;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;

        this.mComposite = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        //no-op
    }

    @Override
    public void unsubscribe() {
        this.mComposite.clear();
    }

    @Override
    public void loadTrailers() {
        mComposite.add(mMovieRepository.getTrailers(mMovieId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(mMovieDetailView::showTrailers,
                        throwable -> {}, () -> {}));
    }
}
