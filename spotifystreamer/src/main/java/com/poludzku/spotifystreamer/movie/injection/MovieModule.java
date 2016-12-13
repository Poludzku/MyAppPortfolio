package com.poludzku.spotifystreamer.movie.injection;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.poludzku.spotifystreamer.movie.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenterImpl;
import com.poludzku.spotifystreamer.movie.view.MovieDetailsAdapter;
import com.poludzku.spotifystreamer.movie.view.MovieDetailsAdapterImpl;
import com.poludzku.spotifystreamer.movie.view.MovieView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jacek on 23/11/2016.
 */

@Module
public class MovieModule {
    private MovieView view;

    public MovieModule(MovieView view) {
        this.view = view;
    }

    @Provides
    MoviePresenter moviePresenter(MoviePresenterImpl moviePresenter) {
        return moviePresenter;
    }

    @Provides
    LoadCommentsUseCase loadCommentsUseCase() {
        return new LoadCommentsUseCase();
    }

    @Provides
    MovieView view() {
        return view;
    }
    @Provides
    RecyclerView.LayoutManager linearLayoutManager(){
        return new LinearLayoutManager(null, LinearLayoutManager.VERTICAL, false);
    }
    @Provides
    MovieDetailsAdapter adapter(){
        return new MovieDetailsAdapterImpl();
    }
}
