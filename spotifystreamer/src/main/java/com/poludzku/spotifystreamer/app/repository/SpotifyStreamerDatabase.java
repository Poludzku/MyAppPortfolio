package com.poludzku.spotifystreamer.app.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jacek on 09/02/2017.
 */

public class SpotifyStreamerDatabase extends SQLiteOpenHelper {

    private static final int CURRENT_VERSION = 1;

    public static final String ID = "_id";
    public static final String TITLE = "title";

    private SpotifyStreamerDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SpotifyStreamerDatabase create(Context context){
        return new SpotifyStreamerDatabase(context, "spotifystreamer", null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE favourites (_id INTEGER PRIMARY KEY NOT NULL, title TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
