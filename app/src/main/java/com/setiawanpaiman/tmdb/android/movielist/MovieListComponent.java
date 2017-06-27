package com.setiawanpaiman.tmdb.android.movielist;

import com.setiawanpaiman.tmdb.android.di.component.ApplicationComponent;
import com.setiawanpaiman.tmdb.android.util.ActivityScoped;

import dagger.Component;

/**
 * Created by Setiawan Paiman on 25/6/17.
 */
@ActivityScoped
@Component(modules = MovieListModule.class, dependencies = ApplicationComponent.class)
public interface MovieListComponent {

    void inject(MovieListActivity activity);
}
