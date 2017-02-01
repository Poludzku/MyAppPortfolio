package com.poludzku.spotifystreamer.app.injection;

import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.injection.scopes.PerApplication;
import com.poludzku.spotifystreamer.dashboard.injection.DashboardComponent;
import com.poludzku.spotifystreamer.dashboard.injection.DashboardModule;
import com.poludzku.spotifystreamer.moviedetails.injection.MovieComponent;
import com.poludzku.spotifystreamer.moviedetails.injection.MovieModule;

import dagger.Component;

/**
 * Created by Jacek on 23/11/2016.
 */
@PerApplication
@Component(modules = {SpotifystreamerModule.class, RetrofitModule.class})
public interface SpotifystreamerComponent {
    void inject(SpotifystreamerApplication application);

    DashboardComponent plus(DashboardModule module);

    MovieComponent plus(MovieModule module);
}
