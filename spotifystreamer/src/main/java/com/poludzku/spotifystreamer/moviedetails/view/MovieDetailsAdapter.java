package com.poludzku.spotifystreamer.moviedetails.view;

import android.support.v7.widget.RecyclerView;

import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.moviedetails.repository.MovieDetails;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;

/**
 * Created by Jacek on 13/12/2016.
 */

public abstract class MovieDetailsAdapter extends RecyclerView.Adapter<AbstractMovieViewHolder> {
    abstract void setDetails(MovieDetails movieDetails, Movie movie);

}
