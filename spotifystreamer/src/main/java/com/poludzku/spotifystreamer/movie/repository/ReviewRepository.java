package com.poludzku.spotifystreamer.movie.repository;

import rx.Observable;

/**
 * Created by Jacek on 01/02/2017.
 */

public interface ReviewRepository {
    Observable<UserReviewResponse> loadComments(long movieId);
}
