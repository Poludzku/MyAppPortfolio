package com.poludzku.spotifystreamer.dashboard.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.greed.spotifystreamer.R;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;
import com.poludzku.spotifystreamer.app.repository.MovieApiFactory;
import com.poludzku.spotifystreamer.app.repository.RetrofitHelper;
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.dashboard.injection.DashboardModule;
import com.poludzku.spotifystreamer.dashboard.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.moviedetails.view.MovieFragment;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboardFragment extends Fragment implements DashboardView, MovieViewHolder.OnClick {

    public static final String TAG = "DashboardFragment";


    private static final int SORT_BY_POPULARITY = 0;
    private static final int SORT_BY_RATING = 1;

    private static final String SORT_ORDER_EXTRA = "sort_order_extra";


    private final MovieAdapter adapter = new MovieAdapter(this);

    private RecyclerView mRecyclerView;

    private int sortOrder = SORT_BY_POPULARITY;

    public static final String FAVOURITES = "FAVOURITES";

    @Inject
    MoviePresenter moviePresenter;

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpotifystreamerApplication.getInstance().getComponent().plus(new DashboardModule(this)).inject(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(adapter);
        if (savedInstanceState != null) {
            sortOrder = savedInstanceState.getInt(SORT_ORDER_EXTRA);
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        switch (sortOrder) {
            case SORT_BY_POPULARITY:
                moviePresenter.downloadMoviesByPopularity();
                break;
            case SORT_BY_RATING:
                moviePresenter.downloadMoviesByRating();
                break;
            default:
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SORT_ORDER_EXTRA, sortOrder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        moviePresenter.cleanup();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                sortOrder = SORT_BY_POPULARITY;
                moviePresenter.downloadMoviesByPopularity();
                return true;
            case R.id.sort_by_rating:
                sortOrder = SORT_BY_RATING;
                moviePresenter.downloadMoviesByRating();
                return true;
            default:
                break;
        }
        return false;
    }


    @Override
    public void onClick(int id) {
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

    @Override
    public void populateMovies(MovieResponse movieResponse) {
        adapter.setMovies(movieResponse.getResults());
        if (movieResponse.getResults().size() > 0 && getResources().getBoolean(R.bool.isTablet)) {
            onClick(0);
        }
    }

    @Override
    public void showDownloadError(Throwable throwable) {
        Log.e("spotifystreamer", "Download failure " + throwable.getLocalizedMessage());
        Toast.makeText(getActivity(), "Couldn't download anything...", Toast.LENGTH_LONG).show();
    }
}
