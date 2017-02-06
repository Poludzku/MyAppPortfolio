package com.poludzku.spotifystreamer.moviedetails.repository;

import java.util.List;

/**
 * Created by Jacek on 06/02/2017.
 */

public class VideoResponse {

    private List<Video> results;

    public VideoResponse(List<Video> results) {
        this.results = results;
    }

    public List<Video> getResults() {
        return results;
    }
}
