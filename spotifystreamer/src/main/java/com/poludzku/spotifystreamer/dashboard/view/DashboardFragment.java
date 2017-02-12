package com.poludzku.spotifystreamer.dashboard.view;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.poludzku.spotifystreamer.app.model.Movie;
import com.poludzku.spotifystreamer.app.model.MovieResponse;
import com.poludzku.spotifystreamer.dashboard.injection.DashboardModule;
import com.poludzku.spotifystreamer.dashboard.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.moviedetails.view.MovieFragment;

import javax.inject.Inject;

public class DashboardFragment extends Fragment implements DashboardView, MovieViewHolder.OnClick {

    public static final String TAG = "DashboardFragment";
    public static final String FAVOURITES = "FAVOURITES";
    private static final int SORT_BY_POPULARITY = 0;
    private static final int SORT_BY_RATING = 1;
    private static final int SORT_BY_FAVOURITES = 2;
    private static final String SORT_ORDER_EXTRA = "sort_order_extra";
    private static final String CURRENT_ID_EXTRA = "current_id_extra";
    private static final String LIST_STATE_EXTRA = "list_state_extra";

    int currentSelectedId;

    @Inject
    MovieAdapter adapter;
    @Inject
    MoviePresenter moviePresenter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    private Parcelable mListState;

    private RecyclerView mRecyclerView;
    private int sortOrder = SORT_BY_POPULARITY;

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpotifystreamerApplication.getInstance().getComponent().plus(new DashboardModule(getActivity(),this, this)).inject(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        if (savedInstanceState != null) {
            sortOrder = savedInstanceState.getInt(SORT_ORDER_EXTRA);
            currentSelectedId = savedInstanceState.getInt(CURRENT_ID_EXTRA);
            mListState = savedInstanceState.getParcelable(LIST_STATE_EXTRA);
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
            case SORT_BY_FAVOURITES:
                moviePresenter.downloadMoviesByFavourites();
                break;
            default:
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SORT_ORDER_EXTRA, sortOrder);
        outState.putInt(CURRENT_ID_EXTRA,currentSelectedId);
        outState.putParcelable(LIST_STATE_EXTRA, mLayoutManager.onSaveInstanceState());
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
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.sort_by_rating:
                sortOrder = SORT_BY_RATING;
                moviePresenter.downloadMoviesByRating();
                mLayoutManager.scrollToPosition(0);
                return true;
            case R.id.sort_by_favourites:
                sortOrder = SORT_BY_FAVOURITES;
                moviePresenter.downloadMoviesByFavourites();
                mLayoutManager.scrollToPosition(0);
                return true;
            default:
                break;
        }
        return false;
    }


    @Override
    public void onClick(int id) {
        currentSelectedId = id;
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
        if(mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
        if (movieResponse.getResults().size() > 0 && getResources().getBoolean(R.bool.isTablet)) {
            onClick(currentSelectedId);
        }
    }

    @Override
    public void showDownloadError(Throwable throwable) {
        Log.e("spotifystreamer", "Download failure " + throwable.getLocalizedMessage());
        Toast.makeText(getActivity(), "Couldn't download anything...", Toast.LENGTH_LONG).show();
    }
}
