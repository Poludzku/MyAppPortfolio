package com.poludzku.spotifystreamer.moviedetails.view;

import com.poludzku.spotifystreamer.moviedetails.repository.MovieDetails;

/**
 * Created by Jacek on 12/12/2016.
 */

public interface MovieView {
    void onShowError(String message);

    void addMovieDetails(MovieDetails movieDetails);

    void onFavouriteChanged(boolean favourite);
}
