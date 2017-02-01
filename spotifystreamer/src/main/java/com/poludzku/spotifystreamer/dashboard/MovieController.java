package com.poludzku.spotifystreamer.dashboard;

import com.poludzku.spotifystreamer.app.model.MovieResponse;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieController {
    void downloadMovies();

    void onMoviesDownloaded(MovieResponse movieResponse);
}
