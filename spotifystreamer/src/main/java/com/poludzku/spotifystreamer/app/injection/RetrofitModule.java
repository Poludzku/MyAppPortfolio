package com.poludzku.spotifystreamer.app.injection;

import com.poludzku.spotifystreamer.app.injection.scopes.PerApplication;
import com.poludzku.spotifystreamer.app.repository.MovieApi;
import com.poludzku.spotifystreamer.app.repository.MovieApiFactory;
import com.poludzku.spotifystreamer.app.repository.MoviesRepository;
import com.poludzku.spotifystreamer.app.repository.RetrofitHelper;
import com.poludzku.spotifystreamer.moviedetails.repository.ReviewRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jacek on 01/02/2017.
 */

@Module
public class RetrofitModule {

    @PerApplication
    @Provides
    RetrofitHelper retrofitHelper(MovieApi movieApi) {
        return new RetrofitHelper(movieApi);
    }

    @PerApplication
    @Provides
    ReviewRepository reviewRepository(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @PerApplication
    @Provides
    MoviesRepository moviesRepository(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @PerApplication
    @Provides
    MovieApi movieApi() {
        return new MovieApiFactory().getMovieApi();
    }
}
