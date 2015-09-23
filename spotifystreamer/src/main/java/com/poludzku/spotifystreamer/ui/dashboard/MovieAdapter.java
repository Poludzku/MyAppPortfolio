package com.poludzku.spotifystreamer.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.io.model.Movie;

import java.util.List;

/**
 * Created by greed on 22/09/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies){
        this.movies = movies;
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);

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
