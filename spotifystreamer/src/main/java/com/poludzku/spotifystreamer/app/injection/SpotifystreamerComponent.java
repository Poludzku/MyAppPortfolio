package com.poludzku.spotifystreamer.app.injection;

import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.injection.scopes.PerApplication;
import com.poludzku.spotifystreamer.movie.injection.MovieComponent;
import com.poludzku.spotifystreamer.movie.injection.MovieModule;

import dagger.Component;

/**
 * Created by Jacek on 23/11/2016.
 */
@PerApplication
@Component(modules = {SpotifystreamerModule.class})
public interface SpotifystreamerComponent {
    void inject(SpotifystreamerApplication application);

    MovieComponent plus(MovieModule module);
}
