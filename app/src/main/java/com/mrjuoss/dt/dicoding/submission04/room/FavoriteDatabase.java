package com.mrjuoss.dt.dicoding.submission04.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "favorites_db";

    private static FavoriteDatabase instance;

    static FavoriteDatabase getInstance(final Context context) {
        if (instance == null) {
           instance = Room.databaseBuilder(
                   context.getApplicationContext(),
                   FavoriteDatabase.class,
                   DATABASE_NAME
           ).build();
        }
        return instance;

    }

    public abstract FavoriteDao getFavoriteDao();
}
