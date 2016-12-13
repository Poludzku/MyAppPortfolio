package com.poludzku.spotifystreamer.movie.view;

import com.poludzku.spotifystreamer.movie.repository.UserReviewResponse;

/**
 * Created by Jacek on 12/12/2016.
 */

public interface MovieView {
    void onShowError(String message);

    void addUserReviews(UserReviewResponse userReviewResponse);
}
