package com.poludzku.spotifystreamer.moviedetails.domain;

/**
 * Created by Jacek on 12/02/2017.
 */

public interface ChangeFavouriteUseCase {
    void setMovieFavourite(long id, boolean favourite);
}
