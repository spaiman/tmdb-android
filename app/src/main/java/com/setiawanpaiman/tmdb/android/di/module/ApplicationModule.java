package com.setiawanpaiman.tmdb.android.di.module;

import com.setiawanpaiman.tmdb.android.BuildConfig;
import com.setiawanpaiman.tmdb.android.http.api.ApiFactory;
import com.setiawanpaiman.tmdb.android.http.api.MovieApi;
import com.setiawanpaiman.tmdb.android.util.scheduler.BaseSchedulerProvider;
import com.setiawanpaiman.tmdb.android.util.scheduler.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */
@Module
public class ApplicationModule {

    @Provides
    @Singleton
    ApiFactory providesApiFactory() {
        return new ApiFactory(BuildConfig.BASE_URL_TMDB_API);
    }

    @Provides
    @Singleton
    MovieApi providesMovieApi(final ApiFactory apiFactory) {
        return apiFactory.create(MovieApi.class);
    }

    @Provides
    @Singleton
    BaseSchedulerProvider providesSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
