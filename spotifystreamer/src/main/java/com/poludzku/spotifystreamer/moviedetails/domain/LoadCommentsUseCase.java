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

    private Observable<UserReviewResponse> createLoadCommentsObservable(long movieId){
        return reviewRepository.loadComments(movieId);
    }
    /*private Observable<UserReviewResponse> createLoadCommentsObservable(long movieId){
       UserReview userReview1 = new UserReview("a", "bla", "con");
        UserReview userReview2 = new UserReview("b", "blark", "conasdsdf");
        UserReview userReview3 = new UserReview("c", "asda", "conllklkl");
        UserReview userReview4 = new UserReview("d", "cvnfkg", "con12342q");
        List<UserReview> reviews = new ArrayList<>();
        reviews.add(userReview1);
        reviews.add(userReview2);
        reviews.add(userReview3);
        reviews.add(userReview1);
        reviews.add(userReview2);
        reviews.add(userReview3);
        reviews.add(userReview1);
        reviews.add(userReview2);
        reviews.add(userReview3);
        reviews.add(userReview4);
        reviews.add(userReview4);
        reviews.add(userReview4);
        reviews.add(userReview1);
        reviews.add(userReview2);
        reviews.add(userReview3);
        reviews.add(userReview4);
        UserReviewResponse userReviewResponse = new UserReviewResponse(movieId, 1, 1, 4, reviews );

        return Observable.just(userReviewResponse);
    }*/
}
