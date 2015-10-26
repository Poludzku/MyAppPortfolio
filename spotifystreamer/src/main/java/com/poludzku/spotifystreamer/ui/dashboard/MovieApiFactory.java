package com.poludzku.spotifystreamer.ui.dashboard;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by greed on 25/10/15.
 */
public class MovieApiFactory {
    public MovieApi getMovieApi() {
        return new Retrofit.Builder().baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MovieApi.class);

    }
}
