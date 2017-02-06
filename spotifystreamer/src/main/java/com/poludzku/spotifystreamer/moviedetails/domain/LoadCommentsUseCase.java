package com.poludzku.spotifystreamer.moviedetails.domain;


import com.poludzku.spotifystreamer.moviedetails.repository.ReviewRepository;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jacek on 12/12/2016.
 */

public class LoadCommentsUseCase {
    private LoadCommentsUseCaseCallback callback;
    private ReviewRepository reviewRepository;


    public LoadCommentsUseCase(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void setCallback(LoadCommentsUseCaseCallback callback) {
        this.callback = callback;
    }

    public void execute(long movieId) {
        createLoadCommentsObservable(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResult, this::onError);
    }

    private void onError(Throwable throwable) {
        callback.onUserReviewError(throwable);
    }

    private void onResult(UserReviewResponse userReviewResponse) {
        callback.onUserReviewResponse(userReviewResponse);
    }

    private Observable<UserReviewResponse> createLoadCommentsObservable(long movieId) {
        return reviewRepository.loadComments(movieId);
    }

}
