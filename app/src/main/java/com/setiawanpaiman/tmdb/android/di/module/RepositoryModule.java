package com.setiawanpaiman.tmdb.android.di.module;

import com.setiawanpaiman.tmdb.android.data.source.MovieDataSource;
import com.setiawanpaiman.tmdb.android.data.source.Remote;
import com.setiawanpaiman.tmdb.android.data.source.remote.MovieRemoteDataSource;
import com.setiawanpaiman.tmdb.android.http.api.MovieApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */
@Module(includes = ApplicationModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    @Remote
    MovieDataSource providesMovieRemoteDataSource(MovieApi movieApi) {
        return new MovieRemoteDataSource(movieApi);
    }
}
