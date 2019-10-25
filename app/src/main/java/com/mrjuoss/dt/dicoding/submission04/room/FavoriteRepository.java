package com.mrjuoss.dt.dicoding.submission04.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mrjuoss.dt.dicoding.submission04.room.async.DeleteAsyncTask;
import com.mrjuoss.dt.dicoding.submission04.room.async.InsertAsyncTask;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDatabase mFavoriteDatabase;

    public FavoriteRepository(Context context) {
        mFavoriteDatabase = FavoriteDatabase.getInstance(context);
    }

    public void insertFavorite(Favorite favorite) {
        new InsertAsyncTask(mFavoriteDatabase.getFavoriteDao()).execute(favorite);
    }

    public void updateFavorite(Favorite favorite) {

    }

    public LiveData<List<Favorite>> retrieveFavorites() {
        return mFavoriteDatabase.getFavoriteDao().getFavorites();
    }

    public void deleteFavorite(Favorite favorite) {
        new DeleteAsyncTask(mFavoriteDatabase.getFavoriteDao()).execute(favorite);

    }
}
