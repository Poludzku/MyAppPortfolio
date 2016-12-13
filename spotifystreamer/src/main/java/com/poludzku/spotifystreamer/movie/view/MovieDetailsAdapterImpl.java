package com.poludzku.spotifystreamer.movie.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.movie.repository.UserReview;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacek on 13/12/2016.
 */

public class MovieDetailsAdapterImpl extends MovieDetailsAdapter {
    List<UserReview> reviews = new ArrayList<>();

    @Override
    void setDetails(UserReviewResponse userReviewResponse) {
        reviews.clear();
        reviews.addAll(userReviewResponse.getResults());
        notifyDataSetChanged();

    }

    @Override
    public MovieDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieDetailsViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_movie_review, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieDetailsViewHolder holder, int position) {
        UserReview userReview = reviews.get(position);
        holder.author.setText(userReview.getAuthor());
        holder.content.setText(userReview.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
