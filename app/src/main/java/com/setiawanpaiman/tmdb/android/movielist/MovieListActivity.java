package com.setiawanpaiman.tmdb.android.movielist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.setiawanpaiman.tmdb.android.MovieApplication;
import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.util.ViewUtils;

import java.util.List;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View {

    @Inject MovieListPresenter mPresenter;

    private SwipeRefreshLayout mSwipeRefresh;
    private MovieListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MovieListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, true);

        DaggerMovieListComponent.builder()
                .applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent())
                .movieListModule(new MovieListModule(this))
                .build()
                .inject(this);

        mPresenter.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void clearMovieList() {
        mAdapter.clearData();
    }

    @Override
    public void showMovieList(List<MovieViewModel> movieViewModels) {
        mAdapter.addData(movieViewModels);
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }

    @Override
    public void showError() {
        Snackbar.make(mSwipeRefresh, R.string.error_load_movies, Snackbar.LENGTH_LONG).show();
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }
}
