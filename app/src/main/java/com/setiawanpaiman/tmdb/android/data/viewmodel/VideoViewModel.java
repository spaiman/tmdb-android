package com.setiawanpaiman.tmdb.android.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class VideoViewModel implements Parcelable {

    private String mId;
    private String mThumbnailUrl;
    private String mUrl;
    private String mName;

    public VideoViewModel(String id, String thumbnailUrl, String url, String name) {
        this.mId = id;
        this.mThumbnailUrl = thumbnailUrl;
        this.mUrl = url;
        this.mName = name;
    }

    public String getId() {
        return mId;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getName() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mThumbnailUrl);
        dest.writeString(this.mUrl);
        dest.writeString(this.mName);
    }

    protected VideoViewModel(Parcel in) {
        this.mId = in.readString();
        this.mThumbnailUrl = in.readString();
        this.mUrl = in.readString();
        this.mName = in.readString();
    }

    public static final Creator<VideoViewModel> CREATOR = new Creator<VideoViewModel>() {
        @Override
        public VideoViewModel createFromParcel(Parcel source) {
            return new VideoViewModel(source);
        }

        @Override
        public VideoViewModel[] newArray(int size) {
            return new VideoViewModel[size];
        }
    };
}
