package com.poludzku.spotifystreamer.ui.dashboard;

import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.List;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieController {
    void downloadMovies();

    void onMoviesDownloaded(List<Movie> movies);
}
