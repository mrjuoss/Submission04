package com.mrjuoss.dt.dicoding.submission04.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.sql.Struct;
import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(Favorite favorite);

    @Query("SELECT * FROM favorites")
    LiveData<List<Favorite>> getFavorites();

    @Query("SELECT * FROM favorites WHERE favorite_type LIKE :type")
    LiveData<List<Favorite>> getFavoritesByType(String type);

    @Query("SELECT * FROM favorites WHERE title LIKE :title")
    List<Favorite> getFavoriteByCustomQuery(String title);

    @Delete
    int deleteFavorite(Favorite favorite);

    @Query("DELETE FROM favorites")
    void deleteAll();

}
