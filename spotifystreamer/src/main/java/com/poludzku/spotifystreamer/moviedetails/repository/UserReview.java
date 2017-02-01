package com.poludzku.spotifystreamer.moviedetails.repository;

/**
 * Created by Jacek on 12/12/2016.
 */

public class UserReview {
    private String id;
    private String author;
    private String content;

    public UserReview(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
