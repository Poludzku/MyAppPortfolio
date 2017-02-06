package com.poludzku.spotifystreamer.moviedetails.view;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.dashboard.view.MovieViewHolder;
import com.poludzku.spotifystreamer.moviedetails.repository.MovieDetails;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReview;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;
import com.poludzku.spotifystreamer.moviedetails.repository.Video;
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
    public static final int VIDEO = 2;
    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500/";
    Picasso picasso;
    OpenVideoCallback callback;
    MovieView movieView;
    Resources resources;
    List<Wrapper> reviews = new ArrayList<>();

    @Inject
    public MovieDetailsAdapterImpl(Picasso picasso, MovieView movieView, Resources resources, OpenVideoCallback callback) {
        this.picasso = picasso;
        this.movieView = movieView;
        this.resources = resources;
        this.callback = callback;
    }

    @Override
    void setDetails(MovieDetails movieDetails, Movie movie) {
        reviews.clear();
        reviews.add(new Wrapper(movie));
        for (Video video : movieDetails.getVideoResponse().getResults()){
            reviews.add(new Wrapper(video));
        }
        for (UserReview userReview : movieDetails.getUserReviewResponse().getResults()) {
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
            case VIDEO:
                return new MovieVideoViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_video, parent, false));

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
                detailsHolder.favouriteCheckbox.setChecked(movie.isFavourite());
                detailsHolder.favouriteCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    checkedChanged(isChecked);
                    movie.setFavourite(isChecked);
                });

                picasso.load(getPosterPath(movie.getMoviePoster())).into(detailsHolder.movieDetailsPoster);
                break;
            case VIDEO:
                Video video = reviews.get(position).video;
                MovieVideoViewHolder movieVideoViewHolder = (MovieVideoViewHolder) holder;
                movieVideoViewHolder.openTrailer.setText(String.format(resources.getString(R.string.open_video), video.getType()));
                movieVideoViewHolder.openTrailer.setOnClickListener(v -> openVideo(video.getKey()));
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public int getItemViewType(int position) {
       if (position == 0){
           return HEADER;
       }
       if (reviews.get(position).video != null){
           return VIDEO;
       }
       return REVIEW;
    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }

    private void checkedChanged(boolean favourite) {
        movieView.onFavouriteChanged(favourite);
    }

    private void openVideo(String key){
        callback.openVideo(key);
    }

    static class Wrapper {
        Movie movie;
        UserReview userReview;
        Video video;

        public Wrapper(Video video) {
            this.video = video;
        }

        public Wrapper(Movie movie) {
            this.movie = movie;
        }

        public Wrapper(UserReview userReview) {
            this.userReview = userReview;
        }
    }
}
