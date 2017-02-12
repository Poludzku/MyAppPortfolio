package com.poludzku.spotifystreamer.dashboard.domain;

import android.content.ContentResolver;
import android.database.Cursor;

import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.app.repository.FavouritesContentProvider;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by Jacek on 05/02/2017.
 */

abstract public class AbstractDownloadMoviesUseCase implements DownloadMoviesUseCase {
    private final Scheduler mainThreadScheduler;
    private final Scheduler ioThreadScheduler;
    private final ContentResolver contentResolver;

    private DownloadMoviesUseCaseCallback callback;
    private Subscription subscription;

    public AbstractDownloadMoviesUseCase(ContentResolver contentResolver, Scheduler mainThreadScheduler, Scheduler ioThreadScheduler) {
        this.contentResolver = contentResolver;
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
                .map(this::filterFavourites)
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

    private MovieResponse mapFavourites(MovieResponse origin) {
        Cursor cursor = contentResolver.query(FavouritesContentProvider.URI, null, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {
            return origin;
        }
        while (cursor.moveToNext()) {
            for (Movie movie : origin.getResults()) {
                if (movie.getId() == cursor.getLong(0)) {
                    movie.setFavourite(true);
                    break;
                }
            }
        }
        return origin;
    }

    protected MovieResponse filterFavourites(MovieResponse origin) {
        return origin;
    }

    private void onMoviesDownloaded(MovieResponse movieResponse) {
        callback.onMoviesDownloaded(movieResponse);
    }

    private void onMoviesDownloadError(Throwable throwable) {
        callback.onMoviesDownloadError(throwable);
    }


}
