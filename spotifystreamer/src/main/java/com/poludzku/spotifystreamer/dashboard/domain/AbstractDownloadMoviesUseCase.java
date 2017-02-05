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
 * Created by Jacek on 05/02/2017.
 */

abstract public class AbstractDownloadMoviesUseCase implements DownloadMoviesUseCase{
    private final Scheduler mainThreadScheduler;
    private final Scheduler ioThreadScheduler;

    private DownloadMoviesUseCaseCallback callback;
    private Subscription subscription;

    public AbstractDownloadMoviesUseCase(Scheduler mainThreadScheduler,Scheduler ioThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.ioThreadScheduler = ioThreadScheduler;
    }

    public void setCallback(DownloadMoviesUseCaseCallback callback) {
        this.callback = callback;
    }

    public void execute() {
        Observable<MovieResponse> observable = createDownloadObservable();
        subscription = observable
                .observeOn(mainThreadScheduler)
                .subscribeOn(ioThreadScheduler)
                .map(this::mapFavourites)
                .subscribe(
                        this::onMoviesDownloaded,
                        this::onMoviesDownloadError
                );
    }

    abstract public Observable<MovieResponse> createDownloadObservable();

    public void cleanup() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
        callback = null;
    }

    abstract MovieResponse mapFavourites(MovieResponse origin);

    private void onMoviesDownloaded(MovieResponse movieResponse) {
        callback.onMoviesDownloaded(movieResponse);
    }

    private void onMoviesDownloadError(Throwable throwable) {
        callback.onMoviesDownloadError(throwable);
    }


}
