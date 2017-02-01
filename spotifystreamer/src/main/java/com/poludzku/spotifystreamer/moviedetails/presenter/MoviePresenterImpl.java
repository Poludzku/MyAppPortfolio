package com.poludzku.spotifystreamer.moviedetails.presenter;

import com.poludzku.spotifystreamer.moviedetails.domain.ChangeFavouriteUseCase;
import com.poludzku.spotifystreamer.moviedetails.domain.LoadCommentsUseCase;
import com.poludzku.spotifystreamer.moviedetails.domain.LoadCommentsUseCaseCallback;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;
import com.poludzku.spotifystreamer.moviedetails.view.MovieView;

import javax.inject.Inject;

/**
 * Created by Jacek on 12/12/2016.
 */

public class MoviePresenterImpl implements MoviePresenter, LoadCommentsUseCaseCallback {

    LoadCommentsUseCase loadCommentsUseCase;
    ChangeFavouriteUseCase changeFavouriteUseCase;
    MovieView view;

    @Inject
    public MoviePresenterImpl(LoadCommentsUseCase loadCommentsUseCase, MovieView view, ChangeFavouriteUseCase changeFavouriteUseCase) {
        this.loadCommentsUseCase = loadCommentsUseCase;
        this.loadCommentsUseCase.setCallback(this);
        this.view = view;
        this.changeFavouriteUseCase = changeFavouriteUseCase;
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

    @Override
    public void changeFavourite(long id, boolean favourite) {
        changeFavouriteUseCase.setMovieFavourite(id, favourite);
    }
}
