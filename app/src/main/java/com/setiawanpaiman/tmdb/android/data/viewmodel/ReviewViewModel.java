package com.setiawanpaiman.tmdb.android.data.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Setiawan Paiman on 28/7/17.
 */

public class ReviewViewModel implements Parcelable {

    private String mId;
    private String mAuthor;
    private String mContent;

    public ReviewViewModel(String mId, String mAuthor, String mContent) {
        this.mId = mId;
        this.mAuthor = mAuthor;
        this.mContent = mContent;
    }

    public String getId() {
        return mId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getContent() {
        return mContent;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mAuthor);
        dest.writeString(this.mContent);
    }

    protected ReviewViewModel(Parcel in) {
        this.mId = in.readString();
        this.mAuthor = in.readString();
        this.mContent = in.readString();
    }

    public static final Creator<ReviewViewModel> CREATOR = new Creator<ReviewViewModel>() {
        @Override
        public ReviewViewModel createFromParcel(Parcel source) {
            return new ReviewViewModel(source);
        }

        @Override
        public ReviewViewModel[] newArray(int size) {
            return new ReviewViewModel[size];
        }
    };
}
