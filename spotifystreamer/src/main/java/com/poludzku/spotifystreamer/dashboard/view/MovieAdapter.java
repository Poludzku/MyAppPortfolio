package com.poludzku.spotifystreamer.dashboard.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.app.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greed on 22/09/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private static final String IMAGE_PATH = "http://image.tmdb.org/t/p/w500/";
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
        //TODO
        //SpotifystreamerApplication.getInstance()
          //      .getPicasso()
            //    .load(getPosterPath(movie.getMoviePoster()))
              //  .into(holder.imageView);

    }

    @Override
    public void onViewDetachedFromWindow(MovieViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //TODO
        //SpotifystreamerApplication.getInstance().getPicasso().cancelRequest(holder.imageView);
        holder.imageView.setImageDrawable(null);
    }

    private String getPosterPath(String image) {
        return IMAGE_PATH + image;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
