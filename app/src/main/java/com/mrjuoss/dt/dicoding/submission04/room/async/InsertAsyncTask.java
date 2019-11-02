package com.mrjuoss.dt.dicoding.submission04.room.async;

import android.os.AsyncTask;

import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteDao;

public class InsertAsyncTask extends AsyncTask<Favorite, Void, Void> {

    private FavoriteDao mFavoriteDao;

    public InsertAsyncTask(FavoriteDao dao) {
        this.mFavoriteDao = dao;
    }

    @Override
    protected Void doInBackground(Favorite... favorites) {
        mFavoriteDao.insert(favorites[0]);
        return null;
    }
}
