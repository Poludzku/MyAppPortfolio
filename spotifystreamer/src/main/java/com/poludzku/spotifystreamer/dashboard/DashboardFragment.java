package com.poludzku.spotifystreamer.dashboard;

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
import com.poludzku.spotifystreamer.movie.view.MovieFragment;

import java.util.HashSet;
import java.util.Set;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboardFragment extends Fragment implements MovieController, MovieViewHolder.OnClick {

    public static final String TAG = "DashboardFragment";


    private static final int SORT_BY_POPULARITY = 0;
    private static final int SORT_BY_RATING = 1;
    private static final String SORT_ORDER_EXTRA = "sort_order_extra";


    private final MovieAdapter adapter = new MovieAdapter(this);

    private RetrofitHelper retrofitHelper;
    private Subscription subscription;
    private RecyclerView mRecyclerView;

    private int sortOrder = SORT_BY_POPULARITY;

    public static final String FAVOURITES = "FAVOURITES";

    private SharedPreferences sharedPreferences;

    public static DashboardFragment getInstance() {
        return new DashboardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        setHasOptionsMenu(true);
        sharedPreferences = SpotifystreamerApplication.getInstance().getSharedPreferences();
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
        downloadMovies();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SORT_ORDER_EXTRA, sortOrder);
    }

    @Override
    public void onDestroy() {
        subscription.unsubscribe();
        retrofitHelper = null;
        subscription = null;
        super.onDestroy();
    }

    private void inject() {
        retrofitHelper = new RetrofitHelper(new MovieApiFactory().getMovieApi());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popularity:
                sortOrder = SORT_BY_POPULARITY;
                downloadMovies();
                return true;
            case R.id.sort_by_rating:
                sortOrder = SORT_BY_RATING;
                downloadMovies();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void downloadMovies() {
        Observable<MovieResponse> observable =
                (sortOrder == SORT_BY_POPULARITY) ? retrofitHelper.downloadMoviesByPopularity()
                        : retrofitHelper.downloadMoviesByRating();
        subscription = observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(this::mapFavourites)
                .subscribe(
                        this::onMoviesDownloaded,
                        throwable -> {
                            Log.e("spotifystreamer", "networksucks " + throwable.getLocalizedMessage());
                            Toast.makeText(getActivity(), "Couldn't download anything...", Toast.LENGTH_LONG).show();
                        }
                );
    }

    private MovieResponse mapFavourites(MovieResponse origin) {

        Set<String> favourites = sharedPreferences.getStringSet(FAVOURITES,new HashSet<>());
        if(favourites.size() == 0) return origin;

        for(Movie movie : origin.getResults()) {
            for(String favouriteId : favourites) {
                if(Integer.valueOf(favouriteId).longValue() == movie.getId()) {
                    movie.setFavourite(true);
                    break;
                }
            }
        }
        return origin;
    }


    @Override
    public void onMoviesDownloaded(MovieResponse movieResponse) {
        adapter.setMovies(movieResponse.getResults());
        if (movieResponse.getResults().size() > 0 && getResources().getBoolean(R.bool.isTablet)) {
            onClick(0);
        }
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
}
