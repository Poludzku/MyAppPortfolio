package com.poludzku.spotifystreamer.movie.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greed.spotifystreamer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jacek on 13/12/2016.
 */

class MovieDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.review_author)
    TextView author;
    @BindView(R.id.review_content)
    TextView content;

    public MovieDetailsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
