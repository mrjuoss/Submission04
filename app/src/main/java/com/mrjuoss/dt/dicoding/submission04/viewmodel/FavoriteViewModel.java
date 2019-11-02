package com.mrjuoss.dt.dicoding.submission04.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteDao;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteDatabase;
import com.mrjuoss.dt.dicoding.submission04.room.async.InsertAsyncTask;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteDao favoriteDao;
    private FavoriteDatabase favoriteDatabase;
    private LiveData<List<Favorite>> mAllFavorites;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);

        favoriteDatabase = FavoriteDatabase.getDatabase(application);
        favoriteDao = favoriteDatabase.favoriteDao();

        mAllFavorites = favoriteDao.getFavorites();
    }

    public void insert(Favorite favorite) {
        new InsertAsyncTask(favoriteDao).execute(favorite);
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return mAllFavorites;
    }

    public LiveData<List<Favorite>> getFavoritesByType(String type) {
        return mAllFavorites;
    }

}
