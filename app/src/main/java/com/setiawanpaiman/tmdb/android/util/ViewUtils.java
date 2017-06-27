package com.setiawanpaiman.tmdb.android.util;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by Setiawan Paiman on 27/6/17.
 */

public final class ViewUtils {

    public static void setSwipeRefreshing(final SwipeRefreshLayout swipeRefreshLayout,
                                          final boolean refreshing) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
