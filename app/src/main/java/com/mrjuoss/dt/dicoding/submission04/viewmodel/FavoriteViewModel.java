package com.mrjuoss.dt.dicoding.submission04.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private FavoriteRepository mRepository;
    private LiveData<List<Favorite>> mAllFavorites;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new FavoriteRepository(application);
        mAllFavorites = mRepository.getAllFavorites();
    }


    public LiveData<List<Favorite>> getAllFavorites() {
        if (mAllFavorites == null) {
            mAllFavorites = new MutableLiveData<List<Favorite>>();
        }
        return mAllFavorites;
    }

    public void insert(Favorite favorite) {
        mRepository.insert(favorite);
    }

    public void delete (Favorite favorite) {
        mRepository.delete(favorite);
    }
}
