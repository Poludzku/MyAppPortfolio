package com.poludzku.spotifystreamer.dashboard.injection;

import com.poludzku.spotifystreamer.app.injection.qualifiers.ForFavourites;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForPopularity;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForRating;
import com.poludzku.spotifystreamer.app.injection.scopes.PerFragment;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByFavouritesUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByPopularityUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesByRatingUseCase;
import com.poludzku.spotifystreamer.dashboard.domain.DownloadMoviesUseCase;
import com.poludzku.spotifystreamer.dashboard.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.dashboard.presenter.MoviePresenterImpl;
import com.poludzku.spotifystreamer.dashboard.view.DashboardView;
import com.poludzku.spotifystreamer.dashboard.view.MovieAdapter;
import com.poludzku.spotifystreamer.dashboard.view.MovieViewHolder;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jacek on 01/02/2017.
 */
@Module
public class DashboardModule {

    private DashboardView dashboardView;
    private MovieViewHolder.OnClick onClick;

    public DashboardModule(DashboardView dashboardView, MovieViewHolder.OnClick onClick) {
        this.dashboardView = dashboardView;
        this.onClick = onClick;
    }

    @PerFragment
    @Provides
    DashboardView dashboardView() {
        return dashboardView;
    }

    @PerFragment
    @Provides
    MoviePresenter moviePresenter(MoviePresenterImpl moviePresenter) {
        return moviePresenter;
    }

    @PerFragment
    @Provides
    @ForPopularity
    DownloadMoviesUseCase downloadMoviesByPopularityUseCase(DownloadMoviesByPopularityUseCase downloadMoviesByPopularityUseCase) {
        return downloadMoviesByPopularityUseCase;
    }

    @PerFragment
    @Provides
    @ForRating
    DownloadMoviesUseCase downloadMoviesByRatingUseCase(DownloadMoviesByRatingUseCase downloadMoviesByPopularityUseCase) {
        return downloadMoviesByPopularityUseCase;
    }

    @PerFragment
    @Provides
    @ForFavourites
    DownloadMoviesUseCase downloadMoviesByFavouritesUseCase(DownloadMoviesByFavouritesUseCase downloadMoviesByPopularityUseCase) {
        return downloadMoviesByPopularityUseCase;
    }

    @PerFragment
    @Provides
    MovieAdapter movieAdapter(Picasso picasso){
        return new MovieAdapter(onClick, picasso);

    }
}
