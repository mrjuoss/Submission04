package com.mrjuoss.dt.dicoding.submission04.room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.mrjuoss.dt.dicoding.submission04.room.async.InsertAsyncTask;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDatabase mFavoriteDatabase;

    public FavoriteRepository(Context context) {
        mFavoriteDatabase = FavoriteDatabase.getInstance(context);
    }

    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> mAllFavorites;

    public void insertFavorite(Favorite favorite) {
        new InsertAsyncTask(mFavoriteDatabase.getFavoriteDao()).execute(favorite);
    }

    public void updateFavorite(Favorite favorite) {

    }

    public LiveData<List<Favorite>> retrieveFavorites() {
        return mFavoriteDatabase.getFavoriteDao().getFavorites();
    }

    public void deleteFavorite(Favorite favorite) {

    }
}
