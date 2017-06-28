package com.setiawanpaiman.tmdb.android.data.source;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */


@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {

}