package com.poludzku.spotifystreamer.movie.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.io.model.Movie;
import com.poludzku.spotifystreamer.movie.injection.MovieModule;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by greed on 01/11/15.
 */
public class MovieFragment extends Fragment implements MovieView {

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

    @Inject
    Picasso picasso;
    @Inject
    MoviePresenter moviePresenter;

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
        SpotifystreamerApplication.getInstance().getComponent().plus(new MovieModule(this)).inject(this);
        picasso.load(getPosterPath(movie.getMoviePoster())).into(movieDetailsPoster);

        movieDetailsTitle.setText(movie.getTitle());
        movieDetailsRelease.setText(movie.getRelease());
        movieDetailsRatingBar.setRating(movie.getVoteAverage() / 2);
        movieDetailsSynopsis.setText(movie.getPlotSynopsis());

        picasso.load(getPosterPath(movie.getBackdropImage())).into(movieDetailsBackdropImage);
        moviePresenter.loadComments(movie.getId());
    }

    @Override
    public void onShowError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addUserReviews(UserReviewResponse userReviewResponse) {

        Toast.makeText(getActivity(), userReviewResponse.getResults().get(0).getAuthor(), Toast.LENGTH_SHORT).show();
    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }
}
