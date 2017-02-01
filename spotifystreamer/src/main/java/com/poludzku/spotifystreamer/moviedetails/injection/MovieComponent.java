package com.poludzku.spotifystreamer.moviedetails.injection;

import com.poludzku.spotifystreamer.app.injection.scopes.PerFragment;
import com.poludzku.spotifystreamer.moviedetails.view.MovieFragment;

import dagger.Subcomponent;

/**
 * Created by Jacek on 23/11/2016.
 */
@PerFragment
@Subcomponent(modules = MovieModule.class)
public interface MovieComponent {
    void inject(MovieFragment fragment);
}
