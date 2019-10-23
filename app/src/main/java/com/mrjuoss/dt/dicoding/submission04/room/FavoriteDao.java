package com.mrjuoss.dt.dicoding.submission04.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorite")
    void deleteAll();

    @Query("SELECT * FROM favorite WHERE title LIKE :title LIMIT 1")
    Favorite findByName(String title);

    @Query("SELECT * FROM favorite")
    LiveData<List<Favorite>> getAllFavorites();
}
