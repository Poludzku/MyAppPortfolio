package com.poludzku.spotifystreamer.movie.injection;

import com.poludzku.spotifystreamer.movie.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenterImpl;
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
}
