package com.poludzku.spotifystreamer.moviedetails.repository;

import rx.Observable;

/**
 * Created by Jacek on 06/02/2017.
 */

public interface VideoRepository {
    Observable<VideoResponse> loadVideos(long movieId);
}
