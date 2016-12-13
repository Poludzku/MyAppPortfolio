package com.poludzku.spotifystreamer.movie.repository;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jacek on 12/12/2016.
 */

public class UserReviewResponse {

    private long id;
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;

    private List<UserReview> results;

    public UserReviewResponse(long id, int page, int totalPages, int totalResults, List<UserReview> results) {
        this.id = id;
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.results = results;
    }

    public long getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<UserReview> getResults() {
        return results;
    }
}
