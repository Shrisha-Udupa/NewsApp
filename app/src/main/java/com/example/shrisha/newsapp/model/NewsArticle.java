package com.example.shrisha.newsapp.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shrisha on 8/10/17.
 */

public class NewsArticle implements Parcelable {

    public NewsArticle(String newsTitle, String newsUrl, Bitmap bitmap, String newsDescription) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.bitmap = bitmap;
        this.newsDescription = newsDescription;
    }

    private String newsTitle;
    private String newsDescription;
    private String newsUrl;
    private Bitmap bitmap;

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Bitmap getImageBitmap() {
        return bitmap;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.newsTitle);
        dest.writeString(this.newsDescription);
        dest.writeString(this.newsUrl);
        dest.writeParcelable(this.bitmap, flags);
    }

    protected NewsArticle(Parcel in) {
        this.newsTitle = in.readString();
        this.newsDescription = in.readString();
        this.newsUrl = in.readString();
        this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Parcelable.Creator<NewsArticle> CREATOR = new Parcelable.Creator<NewsArticle>() {
        @Override
        public NewsArticle createFromParcel(Parcel source) {
            return new NewsArticle(source);
        }

        @Override
        public NewsArticle[] newArray(int size) {
            return new NewsArticle[size];
        }
    };
}
