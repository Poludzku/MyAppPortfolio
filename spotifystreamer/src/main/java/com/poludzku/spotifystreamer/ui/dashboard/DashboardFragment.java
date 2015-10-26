package com.poludzku.spotifystreamer.ui.dashboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.io.model.Movie;
import com.poludzku.spotifystreamer.io.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by greed on 19/09/15.
 */
public class DashboardFragment extends Fragment implements MovieController {

    public static final String TAG = "DashboardFragment";
    private static final List<Movie> DUMMY_LIST = new ArrayList<>();

    static {
        DUMMY_LIST.add(new Movie(1, "A1", "B1"));
        DUMMY_LIST.add(new Movie(2, "A2", "B2"));
        DUMMY_LIST.add(new Movie(3, "A3", "B3"));
        DUMMY_LIST.add(new Movie(4, "A4", "B4"));
        DUMMY_LIST.add(new Movie(5, "A5", "B5"));
        DUMMY_LIST.add(new Movie(6, "A6", "B6"));
        DUMMY_LIST.add(new Movie(7, "A7", "B7"));
        DUMMY_LIST.add(new Movie(8, "A8", "B8"));
        DUMMY_LIST.add(new Movie(9, "A9", "B9"));
        DUMMY_LIST.add(new Movie(10, "A10", "B10"));
        DUMMY_LIST.add(new Movie(11, "A11", "B11"));
        DUMMY_LIST.add(new Movie(12, "A12", "B12"));
        DUMMY_LIST.add(new Movie(13, "A13", "B13"));
        DUMMY_LIST.add(new Movie(14, "A14", "B14"));
        DUMMY_LIST.add(new Movie(15, "A15", "B15"));
    }

    RetrofitHelper retrofitHelper;
    Subscription subscription;
    private RecyclerView mRecyclerView;

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setOnClickListener(v -> v.setEnabled(false));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        downloadMovies();
    }

    private void inject() {
        retrofitHelper = new RetrofitHelper(new MovieApiFactory().getMovieApi());
    }

    @Override
    public void downloadMovies() {
        Observable<MovieResponse> observable = retrofitHelper.downloadMovies();
        subscription = observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                movieResponse -> {
                    mRecyclerView.setAdapter(new MovieAdapter(movieResponse.getResults()));
                },
                throwable -> Log.e("spotifystreamer", "networksucks " + throwable.getLocalizedMessage())
        );
    }

    @Override
    public void onMoviesDownloaded(List<Movie> movies) {

    }
}
