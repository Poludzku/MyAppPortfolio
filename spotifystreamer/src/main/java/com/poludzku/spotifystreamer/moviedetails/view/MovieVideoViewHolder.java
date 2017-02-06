package com.poludzku.spotifystreamer.moviedetails.view;

import android.view.View;
import android.widget.Button;

import com.example.greed.spotifystreamer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacek on 06/02/2017.
 */

class MovieVideoViewHolder extends AbstractMovieViewHolder {
    @BindView(R.id.open_trailer)
    Button openTrailer;
    public MovieVideoViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, itemView);
    }
}
