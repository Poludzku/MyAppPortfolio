package com.poludzku.spotifystreamer.app.repository;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.greed.spotifystreamer.BuildConfig;
import com.poludzku.spotifystreamer.app.SpotifystreamerApplication;

import javax.inject.Inject;

/**
 * Created by Jacek on 09/02/2017.
 */

public class FavouritesContentProvider extends ContentProvider {

    public static final Uri URI = Uri.parse("content://"+ BuildConfig.APPLICATION_ID + "/favourites");

    SpotifyStreamerDatabase spotifyStreamerDatabase;

    @Override
    public boolean onCreate() {
        spotifyStreamerDatabase = SpotifyStreamerDatabase.create(this.getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteDatabase sqLiteDatabase=spotifyStreamerDatabase.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT _id, title FROM favourites", null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = spotifyStreamerDatabase.getWritableDatabase();
        sqLiteDatabase.insertWithOnConflict("favourites", null, contentValues, SQLiteDatabase.CONFLICT_IGNORE );
        return URI;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArguments) {
        SQLiteDatabase sqLiteDatabase = spotifyStreamerDatabase.getWritableDatabase();
        return sqLiteDatabase.delete("favourites", "_id = ?", selectionArguments);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String whereClause, @Nullable String[] selectionArguments) {
        SQLiteDatabase sqLiteDatabase = spotifyStreamerDatabase.getWritableDatabase();
        return sqLiteDatabase.update("favourites", contentValues, whereClause, selectionArguments);
    }
}
