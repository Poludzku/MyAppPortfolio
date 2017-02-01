package com.poludzku.spotifystreamer.app.repository;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.movie.repository.ReviewRepository;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;

import rx.Observable;

/**
 * Created by greed on 06/10/15.
 */
public class RetrofitHelper implements ReviewRepository {
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
    public Observable<UserReviewResponse> loadComments(long movieId){
        return mMovieApi.getUserReviews(movieId, BuildConfig.MOVIE_KEY);
    }

}
