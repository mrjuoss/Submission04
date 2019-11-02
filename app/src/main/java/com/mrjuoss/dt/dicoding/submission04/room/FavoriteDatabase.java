package com.mrjuoss.dt.dicoding.submission04.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    public abstract FavoriteDao favoriteDao();
    private static volatile FavoriteDatabase INSTANCE;

    public static FavoriteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FavoriteDatabase.class,
                            "db_favorites"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

}
