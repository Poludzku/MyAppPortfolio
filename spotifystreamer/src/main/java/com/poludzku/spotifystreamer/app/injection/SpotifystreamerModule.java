package com.poludzku.spotifystreamer.app.injection;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForIoThread;
import com.poludzku.spotifystreamer.app.injection.qualifiers.ForMainThread;
import com.poludzku.spotifystreamer.app.injection.scopes.PerApplication;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class SpotifystreamerModule {

    private SpotifystreamerApplication application;

    public SpotifystreamerModule(SpotifystreamerApplication application) {
        this.application = application;
    }

    @Provides
    Picasso providePicasso() {
        return Picasso.with(application);
    }

    @Provides
    @PerApplication
    SharedPreferences sharedPreferences() {
        return application.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    @Provides
    @PerApplication
    @ForMainThread
    Scheduler mainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @PerApplication
    @ForIoThread
    Scheduler ioThreadScheduler() {
        return Schedulers.io();
    }

}
