package com.poludzku.spotifystreamer.moviedetails.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.moviedetails.injection.MovieModule;
import com.poludzku.spotifystreamer.moviedetails.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.moviedetails.repository.MovieDetails;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;
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

    @BindView(R.id.movie_details_backdrop_image)
    ImageView movieDetailsBackdropImage;
    @BindView(R.id.details_recycle_view)
    RecyclerView detailsRecycleView;

    @Inject
    Picasso picasso;
    @Inject
    MoviePresenter moviePresenter;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    MovieDetailsAdapter adapter;

    Movie movie;


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
        movie = getArguments().getParcelable(MOVIE_EXTRA);

        ButterKnife.bind(this, view);
        SpotifystreamerApplication.getInstance().getComponent().plus(new MovieModule(this)).inject(this);
        picasso.load(getPosterPath(movie.getBackdropImage())).into(movieDetailsBackdropImage);
        detailsRecycleView.setLayoutManager(layoutManager);
        detailsRecycleView.setAdapter(adapter);
        moviePresenter.loadComments(movie.getId());
    }

    @Override
    public void onShowError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMovieDetails(MovieDetails movieDetails) {
        adapter.setDetails(movieDetails, movie);
    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }

    @Override
    public void onFavouriteChanged(boolean favourite) {
        moviePresenter.changeFavourite(movie.getId(), favourite);
    }
}
