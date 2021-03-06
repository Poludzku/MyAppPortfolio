package com.poludzku.spotifystreamer.dashboard.domain;

import android.content.ContentResolver;
import android.content.Context;
import android.widget.Toast;

import com.poludzku.spotifystreamer.app.injection.qualifiers.ForIoThread;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForMainThread;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.app.repository.MoviesRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;


/**
 * Created by Jacek on 01/02/2017.
 */

public class DownloadMoviesByFavouritesUseCase extends AbstractDownloadMoviesUseCase {

    private final MoviesRepository moviesRepository;

    @Inject
    public DownloadMoviesByFavouritesUseCase(@ForMainThread Scheduler mainThreadScheduler, @ForIoThread Scheduler ioThreadScheduler, ContentResolver contentResolver, MoviesRepository moviesRepository) {
        super(contentResolver, mainThreadScheduler, ioThreadScheduler);
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Observable<MovieResponse> createDownloadObservable() {
        return moviesRepository.downloadMoviesByPopularity();
    }

    @Override
    public MovieResponse filterFavourites(MovieResponse origin) {
        int index = 0;
        while (index < origin.getResults().size()) {
            if (!origin.getResults().get(index).isFavourite()) {
                origin.getResults().remove(index);
            } else {
                index++;
            }

        }

        return origin;
    }
}
