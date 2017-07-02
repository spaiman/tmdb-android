package com.setiawanpaiman.tmdb.android.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.setiawanpaiman.tmdb.android.MovieApplication;
import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    private static final String EXTRA_MOVIE = MovieDetailActivity.class.getName() + ".EXTRA_MOVIE";

    @Inject MovieDetailPresenter mPresenter;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull MovieViewModel movie) {
        return new Intent(context, MovieDetailActivity.class)
                .putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        DaggerMovieDetailComponent.builder()
                .applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent())
                .movieDetailModule(new MovieDetailModule(this))
                .build()
                .inject(this);

        bindViews(getIntent().getParcelableExtra(EXTRA_MOVIE));
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
    }
}
