package com.poludzku.spotifystreamer.moviedetails.domain;

import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import static com.poludzku.spotifystreamer.dashboard.view.DashboardFragment.FAVOURITES;

public class PreferencesChangeFavouriteUseCase implements ChangeFavouriteUseCase {

    SharedPreferences sharedPreferences;

    @Inject
    public PreferencesChangeFavouriteUseCase(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void setMovieFavourite(long id, boolean favourite) {
        String idString = Long.toString(id);
        Set<String> current = sharedPreferences.getStringSet(FAVOURITES, new HashSet<>());
        if (favourite && !current.contains(idString)) {
            current.add(idString);
        } else if (!favourite && current.contains(idString)) {
            current.remove(idString);
        }
        sharedPreferences.edit().putStringSet(FAVOURITES, null).commit();
        sharedPreferences.edit().putStringSet(FAVOURITES, current).commit();

    }
}
