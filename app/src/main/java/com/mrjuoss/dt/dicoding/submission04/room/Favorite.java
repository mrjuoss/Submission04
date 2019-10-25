package com.mrjuoss.dt.dicoding.submission04.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int favorite_id;
    private String title;
    private String overview;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "favorite_type")
    private String typeFavorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getTypeFavorite() {
        return typeFavorite;
    }

    public void setTypeFavorite(String typeFavorite) {
        this.typeFavorite = typeFavorite;
    }

    @Ignore
    public Favorite(int id, int favorite_id, String title, String overview, String releaseDate, String posterPath, String backdropPath, String typeFavorite) {
        this.id = id;
        this.favorite_id = favorite_id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.typeFavorite = typeFavorite;
    }

    @Ignore
    public Favorite() {
    }


    public Favorite(int favorite_id, String title, String overview, String releaseDate, String posterPath, String backdropPath, String typeFavorite) {
        this.favorite_id = favorite_id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.typeFavorite = typeFavorite;
    }
}
