package com.poludzku.spotifystreamer.app.model;

import java.util.List;

/**
 * Created by greed on 26/10/15.
 */

public class MovieResponse {
    private int page;
    private List<Movie> results;
    private int total_pages;
    private int total_results;

    public MovieResponse(int page, List<Movie> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
