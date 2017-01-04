package com.poludzku.spotifystreamer.io.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    private long id;
    private String title;
    @SerializedName("release_date")
    private String release;
    @SerializedName("poster_path")
    private String moviePoster;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("overview")
    private String plotSynopsis;
    @SerializedName("backdrop_path")
    private String backdropImage;

    private boolean favourite;

    private Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        release = in.readString();
        moviePoster = in.readString();
        voteAverage = in.readFloat();
        plotSynopsis = in.readString();
        backdropImage = in.readString();
        favourite = in.readInt() == 1;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getPlotSynopsis() {
        return plotSynopsis;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease() {

        return release;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public int describeContents() {
        return 0;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(title);
        out.writeString(release);
        out.writeString(moviePoster);
        out.writeFloat(voteAverage);
        out.writeString(plotSynopsis);
        out.writeString(backdropImage);
        out.writeInt((favourite ? 1 : 0));
    }

}
