package com.poludzku.spotifystreamer.io.model;

/**
 * Created by greed on 19/09/15.
 */
public class Movie {



    private long id;
    private String title;
    private String release;

    public Movie(long id, String title, String release) {
        this.id = id;
        this.title = title;
        this.release = release;
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
}
