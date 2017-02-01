package com.poludzku.spotifystreamer.dashboard.presenter;

import com.poludzku.spotifystreamer.app.injection.qualifiers.ForFavourites;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForPopularity;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForRating;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByFavouritesUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByPopularityUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByRatingUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesUseCaseCallback;
import com.poludzku.spotifystreamer.dashboard.view.DashboardView;

import javax.inject.Inject;

/**
 * Created by Jacek on 01/02/2017.
 */

public class MoviePresenterImpl implements MoviePresenter, DownloadMoviesUseCaseCallback {

    private final DashboardView dashboardView;
    private final DownloadMoviesUseCase downloadMoviesByPopularityUseCase;
    private final DownloadMoviesUseCase downloadMoviesByRatingUseCase;
    private final DownloadMoviesUseCase downloadMoviesByFavouritesUseCase;

    @Inject
    public MoviePresenterImpl(DashboardView dashboardView,
                              @ForPopularity DownloadMoviesUseCase downloadMoviesByPopularityUseCase,
                              @ForRating DownloadMoviesUseCase downloadMoviesByRatingUseCase,
                              @ForFavourites DownloadMoviesUseCase downloadMoviesByFavouritesUseCase) {
        this.dashboardView = dashboardView;
        this.downloadMoviesByPopularityUseCase = downloadMoviesByPopularityUseCase;
        downloadMoviesByPopularityUseCase.setCallback(this);
        this.downloadMoviesByRatingUseCase = downloadMoviesByRatingUseCase;
        downloadMoviesByRatingUseCase.setCallback(this);
        this.downloadMoviesByFavouritesUseCase = downloadMoviesByFavouritesUseCase;
        downloadMoviesByFavouritesUseCase.setCallback(this);
    }

    @Override
    public void downloadMoviesByPopularity() {
        downloadMoviesByPopularityUseCase.execute();

    }

    @Override
    public void downloadMoviesByFavourites() {
        downloadMoviesByFavouritesUseCase.execute();
    }

    @Override
    public void downloadMoviesByRating() {
        downloadMoviesByRatingUseCase.execute();
    }

    @Override
    public void onMoviesDownloaded(MovieResponse movieResponse) {
        dashboardView.populateMovies(movieResponse);
    }

    @Override
    public void onMoviesDownloadError(Throwable throwable) {
        dashboardView.showDownloadError(throwable);
    }

    @Override
    public void cleanup() {
        downloadMoviesByPopularityUseCase.cleanup();
        downloadMoviesByRatingUseCase.cleanup();
        downloadMoviesByFavouritesUseCase.cleanup();
    }
}
