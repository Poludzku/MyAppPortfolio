package com.poludzku.spotifystreamer.movie.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.io.model.Movie;
import com.poludzku.spotifystreamer.movie.repository.UserReview;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jacek on 13/12/2016.
 */

public class MovieDetailsAdapterImpl extends MovieDetailsAdapter {
    public static final int REVIEW = 0;
    public static final int HEADER = 1;
    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500/";
    Picasso picasso;
    List<Wrapper> reviews = new ArrayList<>();

    @Inject
    public MovieDetailsAdapterImpl(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    void setDetails(UserReviewResponse userReviewResponse,Movie movie) {
        reviews.clear();
        reviews.add(new Wrapper(movie));
        for (UserReview userReview: userReviewResponse.getResults()){
            reviews.add(new Wrapper(userReview));
        }
        notifyDataSetChanged();

    }

    @Override
    public AbstractMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case REVIEW:
                return new MovieReviewViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_movie_review, parent, false));
            case HEADER:
                return new MovieDetailsViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_movie_detail, parent, false));

        }
        throw new IllegalArgumentException("Unexpected View Type!!");

    }

    @Override
    public void onBindViewHolder(AbstractMovieViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case REVIEW:
                UserReview userReview = reviews.get(position).userReview;
                MovieReviewViewHolder reviewHolder = (MovieReviewViewHolder) holder;
                reviewHolder.author.setText(userReview.getAuthor());
                reviewHolder.content.setText(userReview.getContent());
                break;
            case HEADER:
                Movie movie = reviews.get(position).movie;
                MovieDetailsViewHolder detailsHolder = (MovieDetailsViewHolder) holder;
                detailsHolder.movieDetailsTitle.setText(movie.getTitle());
                detailsHolder.movieDetailsRelease.setText(movie.getRelease());
                detailsHolder.movieDetailsRatingBar.setRating(movie.getVoteAverage() / 2);
                detailsHolder.movieDetailsSynopsis.setText(movie.getPlotSynopsis());

                picasso.load(getPosterPath(movie.getMoviePoster())).into(detailsHolder.movieDetailsPoster);
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : REVIEW;
    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }

    static class Wrapper{
        Movie movie;
        UserReview userReview;

        public Wrapper(Movie movie) {
            this.movie = movie;
        }

        public Wrapper(UserReview userReview) {
            this.userReview = userReview;
        }
    }
}
