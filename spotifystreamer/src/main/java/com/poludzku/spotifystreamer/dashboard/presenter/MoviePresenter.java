package com.poludzku.spotifystreamer.dashboard.presenter;

/**
 * Created by greed on 25/10/15.
 */
public interface MoviePresenter {

    void downloadMoviesByFavourites();

    void downloadMoviesByPopularity();

    void downloadMoviesByRating();

    void cleanup();
}
