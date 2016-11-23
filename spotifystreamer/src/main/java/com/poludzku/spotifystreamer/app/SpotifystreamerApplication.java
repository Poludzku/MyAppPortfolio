package com.poludzku.spotifystreamer.app;

import android.app.Application;

import com.poludzku.spotifystreamer.app.injection.DaggerSpotifystreamerComponent;
import com.poludzku.spotifystreamer.app.injection.SpotifystreamerComponent;
import com.poludzku.spotifystreamer.app.injection.SpotifystreamerModule;

/**
 * Created by greed on 01/11/15.
 */
public class SpotifystreamerApplication extends Application {

    private static SpotifystreamerApplication instance;

    private SpotifystreamerComponent component;

    public static SpotifystreamerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        component = DaggerSpotifystreamerComponent
                .builder()
                .spotifystreamerModule(new SpotifystreamerModule(this))
                .build();
    }

    public SpotifystreamerComponent getComponent() {
        return component;
    }

}
