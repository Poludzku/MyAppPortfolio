package com.poludzku.spotifystreamer.movie.injection;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.poludzku.spotifystreamer.movie.domain.ChangeFavouriteUseCase;
import com.poludzku.spotifystreamer.movie.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenter;
import com.poludzku.spotifystreamer.movie.presenter.MoviePresenterImpl;
import com.poludzku.spotifystreamer.movie.repository.ReviewRepository;
import com.poludzku.spotifystreamer.movie.view.MovieDetailsAdapter;
import com.poludzku.spotifystreamer.movie.view.MovieDetailsAdapterImpl;
import com.poludzku.spotifystreamer.movie.view.MovieView;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

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
    LoadCommentsUseCase loadCommentsUseCase(ReviewRepository reviewRepository) {
        return new LoadCommentsUseCase(reviewRepository);
    }

    @Provides
    ChangeFavouriteUseCase changeFavouriteUseCase(SharedPreferences sharedPreferences) {
        return new ChangeFavouriteUseCase(sharedPreferences);
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
    MovieDetailsAdapter adapter(Picasso picasso){
        return new MovieDetailsAdapterImpl(picasso,view);
    }
}
