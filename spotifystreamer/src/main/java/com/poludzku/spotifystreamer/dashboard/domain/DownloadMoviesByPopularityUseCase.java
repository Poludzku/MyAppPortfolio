package com.poludzku.spotifystreamer.dashboard.domain;

import android.content.ContentResolver;

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

public class DownloadMoviesByPopularityUseCase extends AbstractDownloadMoviesUseCase {

    private final MoviesRepository moviesRepository;

    @Inject
    public DownloadMoviesByPopularityUseCase(@ForMainThread Scheduler mainThreadScheduler, @ForIoThread Scheduler ioThreadScheduler, ContentResolver contentResolver, MoviesRepository moviesRepository) {
        super(contentResolver, mainThreadScheduler, ioThreadScheduler);
        this.moviesRepository = moviesRepository;
    }

    @Override
    public Observable<MovieResponse> createDownloadObservable() {
        return moviesRepository.downloadMoviesByPopularity();
    }

}
