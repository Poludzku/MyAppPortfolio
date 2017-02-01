package com.poludzku.spotifystreamer.dashboard.injection;

import com.poludzku.spotifystreamer.app.injection.scopes.PerFragment;
import com.poludzku.spotifystreamer.dashboard.view.DashboardFragment;

import dagger.Subcomponent;

/**
 * Created by Jacek on 01/02/2017.
 */
@PerFragment
@Subcomponent(modules = DashboardModule.class)
public interface DashboardComponent {
    void inject(DashboardFragment dashboardFragment);
}
