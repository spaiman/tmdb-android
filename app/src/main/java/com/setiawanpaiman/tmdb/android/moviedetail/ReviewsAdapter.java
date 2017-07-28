package com.setiawanpaiman.tmdb.android.moviedetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.ReviewViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private final List<ReviewViewModel> mData;

    public ReviewsAdapter() {
        mData = new ArrayList<>();
    }

    @Override
    public ReviewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(ReviewsAdapter.ViewHolder holder, int position) {
        ReviewViewModel reviewViewModel = mData.get(position);
        holder.mAuthor.setText(reviewViewModel.getAuthor());
        holder.mContent.setText(reviewViewModel.getContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    List<ReviewViewModel> getData() {
        return new ArrayList<>(mData);
    }

    void addData(final List<ReviewViewModel> data) {
        final int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAuthor;
        private final TextView mContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mAuthor = (TextView) itemView.findViewById(R.id.text_author);
            mContent = (TextView) itemView.findViewById(R.id.text_content);
        }
    }
}
