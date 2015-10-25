package com.poludzku.spotifystreamer.ui.dashboard;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by greed on 06/10/15.
 */
public class RetrofitHelper {
    private MovieApi mMovieApi;

    public RetrofitHelper() {
        mMovieApi = new Retrofit.Builder().baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi.class);
    }

    public Call<List<Movie>> downloadMovies() {
        return mMovieApi.getMovies(BuildConfig.MOVIE_KEY);
    }

    public interface MovieApi {
        @GET("/3/discover/movie?sort_by=popularity.desc")
        Call<List<Movie>> getMovies(@Query("api_key") String key);
    }

}
