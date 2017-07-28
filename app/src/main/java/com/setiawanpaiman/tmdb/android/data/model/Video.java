package com.setiawanpaiman.tmdb.android.data.model;

import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class Video implements IViewModel<VideoViewModel> {

    private static final String YOUTUBE_SITE = "youtube";
    private static final String YOUTUBE_THUMBNAIL_URL_FORMAT = "https://img.youtube.com/vi/%s/0.jpg";
    private static final String YOUTUBE_URL_FORMAT = "http://www.youtube.com/watch?v=%s";

    private String id;
    private String key;
    private String name;
    private String site;

    @Override
    public VideoViewModel toViewModel() {
        return site.equalsIgnoreCase(YOUTUBE_SITE) ?
                new VideoViewModel(id,
                        String.format(YOUTUBE_THUMBNAIL_URL_FORMAT, key),
                        String.format(YOUTUBE_URL_FORMAT, key), name) : null;
    }
}
