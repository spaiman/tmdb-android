package com.setiawanpaiman.tmdb.android.data.viewmodel;

/**
 * Created by Setiawan Paiman on 24/6/17.
 */

public class MovieViewModel {
    private long mId;
    private String mTitle;
    private String mPosterUrl;
    private String mPlotSynopsis;
    private double mUserRating;
    private String mReleaseDate;

    public MovieViewModel(long id, String title, String posterUrl, String plotSynopsis,
                          double userRating, String releaseDate) {
        this.mId = id;
        this.mTitle = title;
        this.mPosterUrl = posterUrl;
        this.mPlotSynopsis = plotSynopsis;
        this.mUserRating = userRating;
        this.mReleaseDate = releaseDate;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPosterUrl() {
        return mPosterUrl;
    }

    public String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    public double getUserRating() {
        return mUserRating;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }
}
