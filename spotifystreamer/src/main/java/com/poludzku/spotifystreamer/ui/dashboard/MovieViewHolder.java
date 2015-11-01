package com.poludzku.spotifystreamer.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.greed.spotifystreamer.R;

/**
 * Created by greed on 22/09/15.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView release;
    OnClick listener;

    public MovieViewHolder(View itemView, OnClick listener) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title_text_view);
        release = (TextView) itemView.findViewById(R.id.release_text_view);
        this.listener = listener;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, getAdapterPosition());
            }
        });

    }

    public interface OnClick {
        void onClick(View caller, int id);
    }
}
