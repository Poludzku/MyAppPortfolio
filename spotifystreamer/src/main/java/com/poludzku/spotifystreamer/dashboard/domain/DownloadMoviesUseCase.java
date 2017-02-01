package com.poludzku.spotifystreamer.dashboard.domain;

/**
 * Created by Jacek on 02/02/2017.
 */

public interface DownloadMoviesUseCase {

    void execute();

    void setCallback(DownloadMoviesUseCaseCallback callback);

    void cleanup();
}
