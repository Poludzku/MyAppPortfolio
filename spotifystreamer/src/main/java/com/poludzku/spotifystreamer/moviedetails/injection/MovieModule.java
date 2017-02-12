package com.poludzku.spotifystreamer.moviedetails.injection;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.poludzku.spotifystreamer.moviedetails.domain.ChangeFavouriteUseCase;
import com.poludzku.spotifystreamer.moviedetails.domain.PreferencesChangeFavouriteUseCase;
import com.poludzku.spotifystreamer.moviedetails.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.moviedetails.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.moviedetails.presenter.MoviePresenterImpl;
import com.poludzku.spotifystreamer.moviedetails.repository.ReviewRepository;
import com.poludzku.spotifystreamer.moviedetails.repository.VideoRepository;
import com.poludzku.spotifystreamer.moviedetails.view.MovieDetailsAdapter;
import com.poludzku.spotifystreamer.moviedetails.view.MovieDetailsAdapterImpl;
import com.poludzku.spotifystreamer.moviedetails.view.MovieView;
import com.poludzku.spotifystreamer.moviedetails.view.OpenVideoCallback;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {
    private MovieView view;
    private OpenVideoCallback callback;

    public MovieModule(MovieView view, OpenVideoCallback callback) {
        this.view = view;
        this.callback = callback;
    }

    @Provides
    MoviePresenter moviePresenter(MoviePresenterImpl moviePresenter) {
        return moviePresenter;
    }

    @Provides
    LoadCommentsUseCase loadCommentsUseCase(ReviewRepository reviewRepository, VideoRepository videoRepository) {
        return new LoadCommentsUseCase(reviewRepository, videoRepository);
    }

    @Provides
    ChangeFavouriteUseCase changeFavouriteUseCase(SharedPreferences sharedPreferences) {
        return new PreferencesChangeFavouriteUseCase(sharedPreferences);
    }

    @Provides
    MovieView view() {
        return view;
    }

    @Provides
    OpenVideoCallback callback(){
        return callback;
    }

    @Provides
    RecyclerView.LayoutManager linearLayoutManager() {
        return new LinearLayoutManager(null, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    MovieDetailsAdapter adapter(Picasso picasso, Resources resources) {
        return new MovieDetailsAdapterImpl(picasso, view, resources, callback);
    }
}
