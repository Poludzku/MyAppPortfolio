package com.poludzku.spotifystreamer.ui.movie;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.io.model.Movie;

/**
 * Created by greed on 01/11/15.
 */
public class MovieFragment extends Fragment {

    public static final String TAG = "MovieFragment";

    private static final String MOVIE_EXTRA = "movie_extra";

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
        ((TextView) view.findViewById(R.id.movie_details_title)).setText(movie.getTitle());

    }
}