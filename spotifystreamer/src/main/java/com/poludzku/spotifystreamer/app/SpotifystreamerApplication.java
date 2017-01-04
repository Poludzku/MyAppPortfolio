package com.poludzku.spotifystreamer.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.poludzku.spotifystreamer.app.injection.DaggerSpotifystreamerComponent;
import com.poludzku.spotifystreamer.app.injection.SpotifystreamerComponent;
import com.poludzku.spotifystreamer.app.injection.SpotifystreamerModule;

import javax.inject.Inject;

/**
 * Created by greed on 01/11/15.
 */
public class SpotifystreamerApplication extends Application {

    private static SpotifystreamerApplication instance;

    private SpotifystreamerComponent component;

    @Inject
    SharedPreferences sharedPreferences;

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
        component.inject(this);
    }

    public SpotifystreamerComponent getComponent() {
        return component;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
