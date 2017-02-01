package com.poludzku.spotifystreamer.dashboard.view;

import com.poludzku.spotifystreamer.app.model.MovieResponse;

/**
 * Created by Jacek on 01/02/2017.
 */

public interface DashboardView {
    void populateMovies(MovieResponse movieResponse);

    void showDownloadError(Throwable throwable);
}
