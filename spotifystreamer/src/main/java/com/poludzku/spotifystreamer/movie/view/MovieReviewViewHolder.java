package com.poludzku.spotifystreamer.movie.view;

import android.view.View;
import android.widget.TextView;

import com.example.greed.spotifystreamer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacek on 14/12/2016.
 */

public class MovieReviewViewHolder extends AbstractMovieViewHolder {
    @BindView(R.id.review_author)
    TextView author;
    @BindView(R.id.review_content)
    TextView content;


    public MovieReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
