package com.setiawanpaiman.tmdb.android.widget;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Setiawan Paiman on 27/6/17.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int mHorizontalSpacing;
    private int mVerticalSpacing;

    public GridSpacingItemDecoration(int horizontalSpacing, int verticalSpacing) {
        this.mHorizontalSpacing = horizontalSpacing;
        this.mVerticalSpacing = verticalSpacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int spanCount = 1;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        outRect.left = column == 0 ? 0 : mHorizontalSpacing / 2;
        outRect.right = column == spanCount - 1 ? 0 : mHorizontalSpacing / 2;
        if (position >= spanCount) {
            outRect.top = mVerticalSpacing; // item top
        }
    }
}

