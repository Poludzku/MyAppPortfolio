package com.poludzku.spotifystreamer.ui.dashboard;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.List;

import retrofit.Call;

/**
 * Created by greed on 06/10/15.
 */
public class RetrofitHelper {
    private MovieApi mMovieApi;

    public RetrofitHelper(MovieApi movieApi) {
        mMovieApi = movieApi;
    }

    public Call<List<Movie>> downloadMovies() {
        return mMovieApi.getMovies(BuildConfig.MOVIE_KEY);
    }

}
