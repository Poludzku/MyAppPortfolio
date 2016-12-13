package com.poludzku.spotifystreamer.movie.view;

import android.support.v7.widget.RecyclerView;

import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;

/**
 * Created by Jacek on 13/12/2016.
 */

public abstract class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsViewHolder> {
    abstract void setDetails(UserReviewResponse userReviewResponse);

}
