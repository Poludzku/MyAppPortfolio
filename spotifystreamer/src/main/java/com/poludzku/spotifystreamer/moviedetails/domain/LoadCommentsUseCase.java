package com.poludzku.spotifystreamer.moviedetails.domain;


import com.poludzku.spotifystreamer.moviedetails.repository.MovieDetails;
import com.poludzku.spotifystreamer.moviedetails.repository.ReviewRepository;
import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;
import com.poludzku.spotifystreamer.moviedetails.repository.VideoRepository;
import com.poludzku.spotifystreamer.moviedetails.repository.VideoResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jacek on 12/12/2016.
 */

public class LoadCommentsUseCase {
    private LoadCommentsUseCaseCallback callback;
    private ReviewRepository reviewRepository;
    private VideoRepository videoRepository;


    public LoadCommentsUseCase(ReviewRepository reviewRepository, VideoRepository videoRepository) {
        this.reviewRepository = reviewRepository;
        this.videoRepository = videoRepository;
    }

    public void setCallback(LoadCommentsUseCaseCallback callback) {
        this.callback = callback;
    }

    public void execute(long movieId) {
        createLoadCommentsObservable(movieId)
                .zipWith(createLoadVideoObservable(movieId), MovieDetails::new)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResult, this::onError);
    }

    private void onError(Throwable throwable) {
        callback.onUserReviewError(throwable);
    }

    private void onResult(MovieDetails movieDetails) {
        callback.onMovieDetailsResponse(movieDetails);
    }

    private Observable<UserReviewResponse> createLoadCommentsObservable(long movieId) {
        return reviewRepository.loadComments(movieId);
    }
    private Observable<VideoResponse> createLoadVideoObservable(long movieId){
        return videoRepository.loadVideos(movieId);
    }

}
