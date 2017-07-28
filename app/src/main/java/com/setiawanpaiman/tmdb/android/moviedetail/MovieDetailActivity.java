package com.setiawanpaiman.tmdb.android.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.setiawanpaiman.tmdb.android.MovieApplication;
import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;
import com.setiawanpaiman.tmdb.android.movielist.MovieListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    private static final String EXTRA_MOVIE = MovieDetailActivity.class.getName() + ".EXTRA_MOVIE";
    static final String STATE_TRAILERS = MovieListActivity.class.getName() + "STATE_TRAILERS";

    @Inject MovieDetailPresenter mPresenter;

    private TrailersAdapter mTrailersAdapter;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull MovieViewModel movie) {
        return new Intent(context, MovieDetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        MovieViewModel movieViewModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        DaggerMovieDetailComponent.builder()
                .applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent())
                .movieDetailModule(new MovieDetailModule(movieViewModel, this))
                .build()
                .inject(this);

        bindViews(movieViewModel);
        mPresenter.subscribe();
        if (savedInstanceState == null) {
            mPresenter.loadTrailers();
        } else {
            mTrailersAdapter.addData(savedInstanceState.getParcelableArrayList(STATE_TRAILERS));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_TRAILERS, new ArrayList<>(mTrailersAdapter.getData()));
    }

    @Override
    public void showTrailers(List<VideoViewModel> videoViewModels) {
        findViewById(R.id.text_trailers).setVisibility(videoViewModels.isEmpty() ? View.GONE : View.VISIBLE);
        mTrailersAdapter.addData(videoViewModels);
    }

    private void bindViews(@NonNull MovieViewModel moviewViewModel) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(moviewViewModel.getTitle());
        ((TextView) findViewById(R.id.text_rating)).setText(String.valueOf(moviewViewModel.getUserRating()));
        ((TextView) findViewById(R.id.text_release_date)).setText(String.valueOf(moviewViewModel.getReleaseDate()));
        ((TextView) findViewById(R.id.text_synopsis)).setText(moviewViewModel.getPlotSynopsis());
        Picasso.with(this)
                .load(moviewViewModel.getPosterUrl())
                .fit()
                .into((ImageView) findViewById(R.id.img_poster));

        RecyclerView trailersRecycler = (RecyclerView) findViewById(R.id.recycler_view_trailers);
        trailersRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mTrailersAdapter = new TrailersAdapter(this);
        trailersRecycler.setAdapter(mTrailersAdapter);
    }
}
