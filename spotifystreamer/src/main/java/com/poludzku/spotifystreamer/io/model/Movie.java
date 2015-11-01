package com.poludzku.spotifystreamer.io.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by greed on 19/09/15.
 */
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
    private String release;

    public Movie(long id, String title, String release) {
        this.id = id;
        this.title = title;
        this.release = release;
    }

    private Movie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        release = in.readString();
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(title);
        out.writeString(release);
    }
}
