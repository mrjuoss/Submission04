package com.mrjuoss.dt.dicoding.submission04.room.async;

import android.os.AsyncTask;
import android.util.Log;

import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteDao;

public class DeleteAsyncTask extends AsyncTask<Favorite, Void, Void> {

    private static final String TAG = "InsertAsyncTask";
    private FavoriteDao mFavoriteDao;

    public DeleteAsyncTask(FavoriteDao dao) {
        mFavoriteDao = dao;
    }

    @Override
    protected Void doInBackground(Favorite... favorites) {
        Log.d(TAG, "doInBackground: thread " + Thread.currentThread().getName());
        mFavoriteDao.deleteFavorite(favorites);
        return null;
    }
}
