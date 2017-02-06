package com.poludzku.spotifystreamer.moviedetails.repository;

/**
 * Created by Jacek on 06/02/2017.
 */

public class Video {
    private String key;
    private String type;

    public Video(String key, String type) {
        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }
}
