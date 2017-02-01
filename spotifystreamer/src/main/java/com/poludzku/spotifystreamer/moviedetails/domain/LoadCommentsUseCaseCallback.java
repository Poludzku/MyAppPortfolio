package com.poludzku.spotifystreamer.moviedetails.domain;

import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;

/**
 * Created by Jacek on 12/12/2016.
 */

public interface LoadCommentsUseCaseCallback {
    void onUserReviewError(Throwable throwable);

    void onUserReviewResponse(UserReviewResponse userReviewResponse);
}

