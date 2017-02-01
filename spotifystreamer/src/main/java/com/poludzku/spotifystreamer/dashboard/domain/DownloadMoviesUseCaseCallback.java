package com.poludzku.spotifystreamer.dashboard.domain;

import com.poludzku.spotifystreamer.app.model.MovieResponse;

/**
 * Created by Jacek on 01/02/2017.
 */

public interface DownloadMoviesUseCaseCallback {

    void onMoviesDownloaded(MovieResponse movieResponse);

    void onMoviesDownloadError(Throwable throwable);
}
