package com.poludzku.spotifystreamer.moviedetails.presenter;

/**
 * Created by Jacek on 12/12/2016.
 */

public interface MoviePresenter {
    void loadComments(long movieId);

    void changeFavourite(long id, String title, boolean favourite);
}
