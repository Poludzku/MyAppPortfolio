package com.poludzku.spotifystreamer.ui.dashboard;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.poludzku.spotifystreamer.ui.movie.MovieFragment;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by greed on 19/09/15.
 */
public class DashboardFragment extends Fragment implements MovieController, MovieViewHolder.OnClick {

    public static final String TAG = "DashboardFragment";

    private final MovieAdapter adapter = new MovieAdapter(this);

    private RetrofitHelper retrofitHelper;
    private Subscription subscription;
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
        mRecyclerView.setAdapter(adapter);
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
        subscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        this::onMoviesDownloaded,
                        throwable -> Log.e("spotifystreamer", "networksucks " + throwable.getLocalizedMessage())
                );
    }

    @Override
    public void onMoviesDownloaded(MovieResponse movieResponse) {
        adapter.setMovies(movieResponse.getResults());
        if (movieResponse.getResults().size() > 0 && getResources().getBoolean(R.bool.isTablet)) {
            onClick(null, 0);
        }
    }

    @Override
    public void onClick(View caller, int id) {
        Log.d("S", "item:" + adapter.getMovie(id) + " id" + id);
        Movie movie = adapter.getMovie(id);
        Fragment topFragment = getFragmentManager().findFragmentByTag(MovieFragment.TAG);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (topFragment != null) {
            getFragmentManager().popBackStackImmediate();
        }
        ft.replace(R.id.details, MovieFragment.getInstance(movie), MovieFragment.TAG);
        ft.addToBackStack(MovieFragment.TAG);
        ft.commit();
    }
}
