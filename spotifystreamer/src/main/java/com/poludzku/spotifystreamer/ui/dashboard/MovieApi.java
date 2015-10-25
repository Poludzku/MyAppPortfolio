package com.poludzku.spotifystreamer.ui.dashboard;

import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by greed on 25/10/15.
 */
public interface MovieApi {
    @GET("/3/discover/movie?sort_by=popularity.desc")
    Call<List<Movie>> getMovies(@Query("api_key") String key);
}
