package com.poludzku.spotifystreamer.ui.dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;

/**
 * Created by greed on 19/09/15.
 */
public class DashboardFragment extends Fragment {

    public static final String TAG = "DashboardFragment";

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return view;
    }
}
