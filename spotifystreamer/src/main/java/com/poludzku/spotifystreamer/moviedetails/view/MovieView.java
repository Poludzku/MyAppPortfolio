package com.poludzku.spotifystreamer.moviedetails.view;

import com.poludzku.spotifystreamer.moviedetails.repository.UserReviewResponse;

/**
 * Created by Jacek on 12/12/2016.
 */

public interface MovieView {
    void onShowError(String message);

    void addUserReviews(UserReviewResponse userReviewResponse);

    void onFavouriteChanged(boolean favourite);
}
