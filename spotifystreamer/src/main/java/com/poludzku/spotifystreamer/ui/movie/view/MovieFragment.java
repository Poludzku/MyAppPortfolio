package com.poludzku.spotifystreamer.ui.movie.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.io.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by greed on 01/11/15.
 */
public class MovieFragment extends Fragment {

    public static final String TAG = "MovieFragment";

    private static final String MOVIE_EXTRA = "movie_extra";

    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500/";

    @BindView(R.id.movie_details_poster)
    ImageView movieDetailsPoster;
    @BindView(R.id.movie_details_title)
    TextView movieDetailsTitle;
    @BindView(R.id.movie_details_release)
    TextView movieDetailsRelease;
    @BindView(R.id.movie_details_rating_bar)
    RatingBar movieDetailsRatingBar;
    @BindView(R.id.movie_details_synopsis)
    TextView movieDetailsSynopsis;
    @BindView(R.id.movie_details_backdrop_image)
    ImageView movieDetailsBackdropImage;

    public static MovieFragment getInstance(Movie movie) {
        MovieFragment fragment = new MovieFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(MOVIE_EXTRA, movie);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Movie movie = getArguments().getParcelable(MOVIE_EXTRA);

        ButterKnife.bind(this, view);
        SpotifystreamerApplication.getInstance().getPicasso()
                .load(getPosterPath(movie.getMoviePoster())).into(movieDetailsPoster);

        movieDetailsTitle.setText(movie.getTitle());
        movieDetailsRelease.setText(movie.getRelease());
        movieDetailsRatingBar.setRating(movie.getVoteAverage() / 2);
        movieDetailsSynopsis.setText(movie.getPlotSynopsis());

        SpotifystreamerApplication.getInstance().getPicasso()
                .load(getPosterPath(movie.getBackdropImage())).into(movieDetailsBackdropImage);

    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }
}
