package com.setiawanpaiman.tmdb.android.movielist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.setiawanpaiman.tmdb.android.MovieApplication;
import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ((MovieApplication) getApplication()).getApplicationComponent()
                .getMovieRepository()
                .getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieViewModel>>() {
                    @Override
                    public void accept(@NonNull List<MovieViewModel> movieViewModels) throws Exception {
                        Log.i("MovieApp", "OnNext");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.i("MovieApp", "OnError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i("MovieApp", "OnFinish");
                    }
                });
    }
}
