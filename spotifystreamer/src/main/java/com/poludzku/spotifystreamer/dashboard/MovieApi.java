package com.poludzku.spotifystreamer.dashboard;

import com.poludzku.spotifystreamer.io.model.MovieResponse;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieApi {

    @GET("/3/discover/movie?sort_by=popularity.desc")
    Observable<MovieResponse> getMoviesByPopularity(@Query("api_key") String key);

    @GET("/3/discover/movie?sort_by=vote_average.desc")
    Observable<MovieResponse> getMoviesByRating(@Query("api_key") String key);

    @GET("/3/movie/{movie_id}/reviews")
    Observable<UserReviewResponse> getUserReviews(@Path("movie_id") long movieId, @Query("api_key") String key);
}
