package com.mrjuoss.dt.dicoding.submission04.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteRepository {

    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> mAllFavorites;

    public FavoriteRepository(Application application) {
        FavoriteDatabase db = FavoriteDatabase.getDatabase(application);
        favoriteDao = db.favoriteDao();
        mAllFavorites = favoriteDao.getAllFavorites();
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return mAllFavorites;
    }

    public void insert (Favorite favorite) {
        new InsertAsyncTask(favoriteDao).execute(favorite);
    }

    public void delete (Favorite favorite) {
        new DeleteAsyncTask(favoriteDao).execute(favorite);
    }

    private static class InsertAsyncTask extends AsyncTask<Favorite, Void, Void> {
        private FavoriteDao mAsyncTaskDao;

        InsertAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Favorite, Void, Void> {
        private FavoriteDao mAsyncTaskDao;

        DeleteAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Favorite... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }


}
