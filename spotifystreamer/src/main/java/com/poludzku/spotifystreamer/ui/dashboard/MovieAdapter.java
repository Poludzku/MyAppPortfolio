package com.poludzku.spotifystreamer.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greed on 22/09/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<Movie> movies = new ArrayList<>();

    private final MovieViewHolder.OnClick listener;

    public MovieAdapter(MovieViewHolder.OnClick listener) {
        this.listener = listener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view, listener);

    }

    public Movie getMovie(int id) {
        return movies.get(id);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.release.setText(movie.getRelease());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
