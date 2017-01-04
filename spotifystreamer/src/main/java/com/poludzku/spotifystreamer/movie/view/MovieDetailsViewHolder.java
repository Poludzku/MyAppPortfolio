package com.poludzku.spotifystreamer.movie.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.greed.spotifystreamer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacek on 14/12/2016.
 */

public class MovieDetailsViewHolder extends AbstractMovieViewHolder {

    @BindView(R.id.movie_details_poster)
    ImageView  movieDetailsPoster;
    @BindView(R.id.movie_details_rating_bar)
    RatingBar movieDetailsRatingBar;
    @BindView(R.id.movie_details_title)
    TextView movieDetailsTitle;
    @BindView(R.id.movie_details_release)
    TextView movieDetailsRelease;
    @BindView(R.id.movie_details_synopsis)
    TextView movieDetailsSynopsis;
    @BindView(R.id.favourite)
    CheckBox favouriteCheckbox;


    public MovieDetailsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
