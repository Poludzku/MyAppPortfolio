package com.poludzku.spotifystreamer.app.repository;

import com.poludzku.spotifystreamer.app.model.MovieResponse;

import rx.Observable;

/**
 * Created by Jacek on 01/02/2017.
 */

public interface MoviesRepository {
    Observable<MovieResponse> downloadMoviesByPopularity();
    Observable<MovieResponse> downloadMoviesByRating();
}
