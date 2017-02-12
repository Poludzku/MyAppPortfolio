package com.poludzku.spotifystreamer.dashboard.domain;

import android.content.ContentResolver;
import android.content.SharedPreferences;

import com.poludzku.spotifystreamer.app.injection.qualifiers.ForIoThread;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForMainThread;
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.app.repository.MoviesRepository;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

import static com.poludzku.spotifystreamer.dashboard.view.DashboardFragment.FAVOURITES;

/**
 * Created by Jacek on 01/02/2017.
 */

public class DownloadMoviesByRatingUseCase extends AbstractDownloadMoviesUseCase{

    private final MoviesRepository moviesRepository;

    @Inject
    public DownloadMoviesByRatingUseCase(@ForMainThread Scheduler mainThreadScheduler, @ForIoThread Scheduler ioThreadScheduler, ContentResolver contentResolver, MoviesRepository moviesRepository) {
        super(contentResolver, mainThreadScheduler, ioThreadScheduler);
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Observable<MovieResponse> createDownloadObservable() {
        return moviesRepository.downloadMoviesByRating();
    }

}
