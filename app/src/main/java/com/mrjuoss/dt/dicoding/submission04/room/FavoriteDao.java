package com.mrjuoss.dt.dicoding.submission04.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite favorite);

    @Update
    void update(Favorite favorite);

    @Delete
    void delete(Favorite favorite);

    @Query("DELETE FROM favorite")
    void deleteAll();

    @Query("SELECT * FROM favorite WHERE title LIKE :title LIMIT 1")
    Favorite findByName(String title);

    @Query("SELECT * FROM favorite ORDER BY id")
    LiveData<List<Favorite>> getAllFavorites();
}
