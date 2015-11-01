package com.poludzku.spotifystreamer.ui.dashboard;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.io.model.MovieResponse;

import rx.Observable;

/**
 * Created by greed on 06/10/15.
 */
public class RetrofitHelper {
    private MovieApi mMovieApi;

    public RetrofitHelper(MovieApi movieApi) {
        mMovieApi = movieApi;
    }

    public Observable<MovieResponse> downloadMoviesByPopularity() {
        return mMovieApi.getMoviesByPopularity(BuildConfig.MOVIE_KEY);
    }

    public Observable<MovieResponse> downloadMoviesByRating() {
        return mMovieApi.getMoviesByRating(BuildConfig.MOVIE_KEY);
    }

}
