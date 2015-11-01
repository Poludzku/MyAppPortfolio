package com.poludzku.spotifystreamer.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.greed.spotifystreamer.R;

/**
 * Created by greed on 22/09/15.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    OnClick listener;

    public MovieViewHolder(View itemView, OnClick listener) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.poster);
        this.listener = listener;
        itemView.setOnClickListener(v -> listener.onClick(v, getAdapterPosition()));

    }

    public interface OnClick {
        void onClick(View caller, int id);
    }
}
