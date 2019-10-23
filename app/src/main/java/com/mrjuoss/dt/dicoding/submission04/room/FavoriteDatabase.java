package com.mrjuoss.dt.dicoding.submission04.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoriteDatabase
    getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class,
                    "favorite_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }



    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FavoriteDao favoriteDao;

        private PopulateDbAsyncTask(FavoriteDatabase db) {
            favoriteDao = db.favoriteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            favoriteDao.insert(new Favorite(1, "Title 1", "overview 1", "release_date 1", "poster_path 1", "backdrop_path 1", "movie"));
            favoriteDao.insert(new Favorite(2, "Title 2", "overview 2", "release_date 2", "poster_path 2", "backdrop_path 2", "movie"));
            favoriteDao.insert(new Favorite(3, "Title 3", "overview 3", "release_date 3", "poster_path 3", "backdrop_path 3", "movie"));
            return null;
        }
    }

}
