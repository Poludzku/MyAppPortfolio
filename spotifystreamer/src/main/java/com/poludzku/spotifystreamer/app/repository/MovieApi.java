package com.poludzku.spotifystreamer.app.repository;

import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;
import com.poludzku.spotifystreamer.moviedetails.repository.VideoResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieApi {

    @GET("/3/movie/popular")
    Observable<MovieResponse> getMoviesByPopularity(@Query("api_key") String key);

    @GET("/3/movie/top_rated")
    Observable<MovieResponse> getMoviesByRating(@Query("api_key") String key);

    @GET("/3/movie/{movie_id}/reviews")
    Observable<UserReviewResponse> getUserReviews(@Path("movie_id") long movieId, @Query("api_key") String key);

    @GET("/3/movie/{movie_id}/videos")
    Observable<VideoResponse> getVideos(@Path("movie_id") long movieId, @Query("api_key") String key);
}
