package com.poludzku.spotifystreamer.dashboard.domain;

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
import rx.Subscription;

import static com.poludzku.spotifystreamer.dashboard.view.DashboardFragment.FAVOURITES;

/**
 * Created by Jacek on 01/02/2017.
 */

public class DownloadMoviesByPopularityUseCase implements DownloadMoviesUseCase {

    private final Scheduler mainThreadScheduler;
    private final Scheduler ioThreadScheduler;
    private final SharedPreferences sharedPreferences;
    private final MoviesRepository moviesRepository;

    private DownloadMoviesUseCaseCallback callback;
    private Subscription subscription;

    @Inject
    public DownloadMoviesByPopularityUseCase(@ForMainThread Scheduler mainThreadScheduler, @ForIoThread Scheduler ioThreadScheduler, SharedPreferences sharedPreferences, MoviesRepository moviesRepository) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
        this.sharedPreferences = sharedPreferences;
        this.moviesRepository = moviesRepository;
    }

    public void setCallback(DownloadMoviesUseCaseCallback callback) {
        this.callback = callback;
    }

    public void execute() {
        Observable<MovieResponse> observable = moviesRepository.downloadMoviesByPopularity();
        subscription = observable
                .observeOn(mainThreadScheduler)
                .subscribeOn(ioThreadScheduler)
                .map(this::mapFavourites)
                .subscribe(
                        this::onMoviesDownloaded,
                        this::onMoviesDownloadError
                );
    }

    public void cleanup() {
        subscription.unsubscribe();
        subscription = null;
        callback = null;
    }

    private MovieResponse mapFavourites(MovieResponse origin) {

        Set<String> favourites = sharedPreferences.getStringSet(FAVOURITES, new HashSet<>());
        if (favourites.size() == 0) return origin;

        for (Movie movie : origin.getResults()) {
            for (String favouriteId : favourites) {
                if (Integer.valueOf(favouriteId).longValue() == movie.getId()) {
                    movie.setFavourite(true);
                    break;
                }
            }
        }
        return origin;
    }

    private void onMoviesDownloaded(MovieResponse movieResponse) {
        callback.onMoviesDownloaded(movieResponse);
    }

    private void onMoviesDownloadError(Throwable throwable) {
        callback.onMoviesDownloadError(throwable);
    }

}
