package com.setiawanpaiman.tmdb.android.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.setiawanpaiman.tmdb.android.MovieApplication;
import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;
import com.setiawanpaiman.tmdb.android.movielist.MovieListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    private static final String EXTRA_MOVIE = MovieDetailActivity.class.getName() + ".EXTRA_MOVIE";
    static final String STATE_TRAILERS = MovieListActivity.class.getName() + "STATE_TRAILERS";
    static final String STATE_REVIEWS = MovieListActivity.class.getName() + "STATE_REVIEWS";

    @Inject MovieDetailPresenter mPresenter;

    private MovieViewModel mMovieViewModel;
    private TrailersAdapter mTrailersAdapter;
    private ReviewsAdapter mReviewsAdapter;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull MovieViewModel movie) {
        return new Intent(context, MovieDetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mMovieViewModel = getIntent().getParcelableExtra(EXTRA_MOVIE);

        DaggerMovieDetailComponent.builder()
                .applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent())
                .movieDetailModule(new MovieDetailModule(mMovieViewModel, this))
                .build()
                .inject(this);

        bindViews(mMovieViewModel);
        mPresenter.subscribe();
        if (savedInstanceState == null) {
            mPresenter.loadTrailers();
            mPresenter.loadReviews();
        } else {
            showTrailers(savedInstanceState.getParcelableArrayList(STATE_TRAILERS));
            showReviews(savedInstanceState.getParcelableArrayList(STATE_REVIEWS));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        boolean isFavorite = mPresenter.isFavoriteMovie(mMovieViewModel);
        menu.findItem(R.id.button_favorite).setVisible(!isFavorite);
        menu.findItem(R.id.button_unfavorite).setVisible(isFavorite);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.button_favorite:
                mPresenter.addFavoriteMovie(mMovieViewModel);
                supportInvalidateOptionsMenu();
                return true;
            case R.id.button_unfavorite:
                mPresenter.removeFavoriteMovie(mMovieViewModel);
                supportInvalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_TRAILERS, new ArrayList<>(mTrailersAdapter.getData()));
        outState.putParcelableArrayList(STATE_REVIEWS, new ArrayList<>(mReviewsAdapter.getData()));
    }

    @Override
    public void showTrailers(List<VideoViewModel> videoViewModels) {
        mTrailersAdapter.addData(videoViewModels);
        findViewById(R.id.progress_trailers).setVisibility(View.GONE);
        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.vs_trailers);
        viewSwitcher.setVisibility(View.VISIBLE);
        viewSwitcher.setDisplayedChild(videoViewModels.isEmpty() ? 1 : 0);
    }

    @Override
    public void showReviews(List<ReviewViewModel> reviewViewModels) {
        mReviewsAdapter.addData(reviewViewModels);
        findViewById(R.id.progress_reviews).setVisibility(View.GONE);
        ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.vs_reviews);
        viewSwitcher.setVisibility(View.VISIBLE);
        viewSwitcher.setDisplayedChild(reviewViewModels.isEmpty() ? 1 : 0);
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

        RecyclerView reviewsRecycler = (RecyclerView) findViewById(R.id.recycler_view_reviews);
        reviewsRecycler.setNestedScrollingEnabled(true);
        reviewsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mReviewsAdapter = new ReviewsAdapter();
        reviewsRecycler.setAdapter(mReviewsAdapter);
    }
}
