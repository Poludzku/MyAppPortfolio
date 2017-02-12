package com.poludzku.spotifystreamer.moviedetails.domain;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.poludzku.spotifystreamer.app.repository.FavouritesContentProvider;
import com.poludzku.spotifystreamer.app.repository.SpotifyStreamerDatabase;

/**
 * Created by Jacek on 12/02/2017.
 */

public class ContentProviderChangeFavouriteUseCase implements ChangeFavouriteUseCase {

    ContentResolver contentResolver;

    @Override
    public void setMovieFavourite(long id, String title, boolean favourite) {
        if(favourite) {
            ContentValues values = new ContentValues();
            values.put(SpotifyStreamerDatabase.ID,id);
            values.put(SpotifyStreamerDatabase.TITLE,title);
            contentResolver.insert(FavouritesContentProvider.URI,values);
        }
    }
}
