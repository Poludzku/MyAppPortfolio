package com.poludzku.spotifystreamer.app.injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.injection.scopes.PerApplication;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class SpotifystreamerModule {

    private SpotifystreamerApplication application;

    public SpotifystreamerModule(SpotifystreamerApplication application){
        this.application = application;
    }

    @Provides
    Picasso providePicasso(){
        return Picasso.with(application);
    }

    @Provides
    @PerApplication
    SharedPreferences sharedPreferences() {
        return application.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }
}
