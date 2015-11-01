package com.poludzku.spotifystreamer.ui.dashboard;

import com.poludzku.spotifystreamer.io.model.MovieResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieApi {

    @GET("/3/discover/movie?sort_by=popularity.desc")
    Observable<MovieResponse> getMoviesByPopularity(@Query("api_key") String key);

    @GET("/3/discover/movie?sort_by=vote_average.desc")
    Observable<MovieResponse> getMoviesByRating(@Query("api_key") String key);

}
