package com.setiawanpaiman.tmdb.android.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.setiawanpaiman.tmdb.android.R;
import com.setiawanpaiman.tmdb.android.data.viewmodel.VideoViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    private final Context mContext;
    private final List<VideoViewModel> mData;

    public TrailersAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trailer, parent, false);
        ViewHolder vh = new ViewHolder(view);
        vh.mThumbnail.setOnClickListener(v -> {
            if (vh.getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }

            VideoViewModel vm = mData.get(vh.getAdapterPosition());
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(vm.getUrl())));
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoViewModel videoViewModel = mData.get(position);
        holder.mTitle.setText(videoViewModel.getName());
        Picasso.with(mContext)
                .load(videoViewModel.getThumbnailUrl())
                .fit()
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    List<VideoViewModel> getData() {
        // defensive copy
        return new ArrayList<>(mData);
    }

    void addData(final List<VideoViewModel> data) {
        final int oldSize = mData.size();
        mData.addAll(data);
        notifyItemRangeInserted(oldSize, data.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mThumbnail;
        private final TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mThumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            mTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
