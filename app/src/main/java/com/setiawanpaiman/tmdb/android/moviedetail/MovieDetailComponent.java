package com.setiawanpaiman.tmdb.android.moviedetail;

import com.setiawanpaiman.tmdb.android.di.component.ApplicationComponent;
import com.setiawanpaiman.tmdb.android.util.ActivityScoped;

import dagger.Component;

/**
 * Created by Setiawan Paiman on 1/7/17.
 */
@ActivityScoped
@Component(modules = MovieDetailModule.class, dependencies = ApplicationComponent.class)
public interface MovieDetailComponent {

    void inject(MovieDetailActivity activity);
}
