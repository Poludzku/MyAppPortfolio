package com.poludzku.spotifystreamer.app.injection;

import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jacek on 23/11/2016.
 */
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
}
