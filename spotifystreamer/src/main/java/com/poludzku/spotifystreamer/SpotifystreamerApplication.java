package com.poludzku.spotifystreamer;

import android.app.Application;

import com.squareup.picasso.Picasso;

/**
 * Created by greed on 01/11/15.
 */
public class SpotifystreamerApplication extends Application {

    private static SpotifystreamerApplication instance;

    private Picasso picasso;

    public static SpotifystreamerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        picasso = Picasso.with(this);
    }

    public Picasso getPicasso() {
        return picasso;
    }
}
