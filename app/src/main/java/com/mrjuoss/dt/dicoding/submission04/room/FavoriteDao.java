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
    long[] insert(Favorite... favorites);

    @Query("SELECT * FROM favorites")
    LiveData<List<Favorite>> getFavorites();

    @Query("SELECT * FROM favorites WHERE title LIKE :title")
    List<Favorite> getFavoriteByCustomQuery(String title);

    @Update
    int update(Favorite... favorites);

    @Delete
    int delete(Favorite... favorites);

    @Query("DELETE FROM favorites")
    void deleteAll();

}
