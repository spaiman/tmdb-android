package com.setiawanpaiman.tmdb.android.di.component;

import com.setiawanpaiman.tmdb.android.data.source.MovieRepository;
import com.setiawanpaiman.tmdb.android.di.module.ApplicationModule;
import com.setiawanpaiman.tmdb.android.di.module.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */
@Singleton
@Component(modules = { ApplicationModule.class, RepositoryModule.class })
public interface ApplicationComponent {

    MovieRepository getMovieRepository();
}
