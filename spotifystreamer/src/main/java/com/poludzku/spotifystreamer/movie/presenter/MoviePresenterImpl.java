package com.poludzku.spotifystreamer.movie.presenter;

import com.poludzku.spotifystreamer.movie.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.movie.domain.LoadCommentsUseCaseCallback;
import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;
import com.poludzku.spotifystreamer.movie.view.MovieView;

import javax.inject.Inject;

/**
 * Created by Jacek on 12/12/2016.
 */

public class MoviePresenterImpl implements MoviePresenter, LoadCommentsUseCaseCallback {

    LoadCommentsUseCase loadCommentsUseCase;
    MovieView view;

    @Inject
    public MoviePresenterImpl(LoadCommentsUseCase loadCommentsUseCase, MovieView view) {
        this.loadCommentsUseCase = loadCommentsUseCase;
        this.loadCommentsUseCase.setCallback(this);
        this.view = view;
    }

    @Override
    public void loadComments(long movieId) {
        loadCommentsUseCase.execute(movieId);
    }

    @Override
    public void onUserReviewError(Throwable throwable) {
        view.onShowError(throwable.getMessage());
    }

    @Override
    public void onUserReviewResponse(UserReviewResponse userReviewResponse) {
        view.addUserReviews(userReviewResponse);
    }
}
