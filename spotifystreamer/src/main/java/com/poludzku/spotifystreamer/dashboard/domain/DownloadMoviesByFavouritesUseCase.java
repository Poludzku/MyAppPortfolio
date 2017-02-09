package com.poludzku.spotifystreamer.dashboard.domain;

import android.content.SharedPreferences;

import com.poludzku.spotifystreamer.app.injection.qualifiers.ForIoThread;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForMainThread;
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.app.repository.MoviesRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

import static com.poludzku.spotifystreamer.dashboard.view.DashboardFragment.FAVOURITES;

/**
 * Created by Jacek on 01/02/2017.
 */

public class DownloadMoviesByFavouritesUseCase extends AbstractDownloadMoviesUseCase{

    private final MoviesRepository moviesRepository;
    private final SharedPreferences sharedPreferences;

    @Inject
    public DownloadMoviesByFavouritesUseCase(@ForMainThread Scheduler mainThreadScheduler, @ForIoThread Scheduler ioThreadScheduler, SharedPreferences sharedPreferences, MoviesRepository moviesRepository) {
        super(mainThreadScheduler, ioThreadScheduler);
        this.moviesRepository = moviesRepository;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<MovieResponse> createDownloadObservable() {
        return moviesRepository.downloadMoviesByPopularity();
    }

    @Override
    public MovieResponse mapFavourites(MovieResponse origin){

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
        Collections.sort(origin.getResults(), ((firstMovie,secondMovie) ->{
            if(firstMovie.isFavourite() == secondMovie.isFavourite()) {
                return 0;
            }
            if(firstMovie.isFavourite()) {
                return -1;
            }
            return 1;
        }));
        return origin;
    }
}
