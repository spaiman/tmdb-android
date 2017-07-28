package com.setiawanpaiman.tmdb.android.data.model;

import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class Review implements IViewModel<ReviewViewModel> {

    private String id;
    private String author;
    private String content;

    @Override
    public ReviewViewModel toViewModel() {
        return new ReviewViewModel(id, author, content);
    }
}
