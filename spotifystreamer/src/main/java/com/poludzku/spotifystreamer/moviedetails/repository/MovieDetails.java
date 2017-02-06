package com.poludzku.spotifystreamer.moviedetails.repository;

/**
 * Created by Jacek on 06/02/2017.
 */

public class MovieDetails {
    private UserReviewResponse userReviewResponse;
    private VideoResponse videoResponse;

    public MovieDetails(UserReviewResponse userReviewResponse, VideoResponse videoResponse) {

        this.userReviewResponse = userReviewResponse;
        this.videoResponse = videoResponse;
    }

    public UserReviewResponse getUserReviewResponse() {
        return userReviewResponse;
    }

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }


}
