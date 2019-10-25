package com.mrjuoss.dt.dicoding.submission04.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.model.movie.ResultsItem;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.room.FavoriteRepository;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MovieDetailActivity";

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final int NEW_FAVORITE_ACTIVITY_REQUEST_CODE = 1;

    private FavoriteViewModel mFavoriteViewModel;
    private FavoriteRepository mFavoriteRepository;

    ProgressBar progressBarDetailMovie;
    ImageView imageDetailPosterMovie;
    TextView textDetailTitleMovie;
    TextView textDetailRatingMovie;
    TextView textDetailreleaseMovie;
    TextView textDetailOverviewMovie;
    Button buttonFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        progressBarDetailMovie = findViewById(R.id.progress_bar_detail_movie);
        imageDetailPosterMovie = findViewById(R.id.image_detail_poster_movie);
        textDetailTitleMovie = findViewById(R.id.text_detail_title_movie);
        textDetailRatingMovie = findViewById(R.id.text_detail_rating_movie);
        textDetailreleaseMovie = findViewById(R.id.text_detail_release_movie);
        textDetailOverviewMovie = findViewById(R.id.text_detail_overview_movie);

        progressBarDetailMovie.setVisibility(View.VISIBLE);

        buttonFavorite = findViewById(R.id.button_favorite_movie);
        buttonFavorite.setTag("add");
        buttonFavorite.setOnClickListener(this);


        Log.d(TAG, "onCreate: thread " + Thread.currentThread().getName());
        showDetail();

    }

    private void showDetail() {
        ResultsItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        String rating = Double.toString(movie.getVoteAverage());
        String url_backdrop = "https://image.tmdb.org/t/p/w185" + movie.getBackdropPath();

        textDetailTitleMovie.setText(movie.getTitle());
        textDetailRatingMovie.setText(rating);
        textDetailreleaseMovie.setText(movie.getReleaseDate());
        textDetailOverviewMovie.setText(movie.getOverview());

        Glide.with(MovieDetailActivity.this)
                .load(url_backdrop)
                .placeholder(R.color.colorAccent)
                .dontAnimate()
                .into(imageDetailPosterMovie);

        progressBarDetailMovie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        mFavoriteRepository = new FavoriteRepository(this);
        if (v.getId() == R.id.button_favorite_movie) {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            startActivityForResult(intent, NEW_FAVORITE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_FAVORITE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ResultsItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
            Favorite favorite = new Favorite(movie.getId(), movie.getTitle(), movie.getOverview(), movie.getReleaseDate(), movie.getPosterPath(), movie.getBackdropPath(), "movie");
            if (buttonFavorite.getTag().toString() == "add") {
                mFavoriteRepository.insertFavorite(favorite);
                Toast.makeText(this, "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                buttonFavorite.setTag("remove");
                buttonFavorite.setText(R.string.remove_favorite);
            } else {
                mFavoriteRepository.deleteFavorite(favorite);
                Toast.makeText(this, "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
                buttonFavorite.setTag("add");
                buttonFavorite.setText(R.string.add_favorite);
            }

        } else {
            Toast.makeText(this, "Gagal Simpan Data", Toast.LENGTH_SHORT).show();
        }
    }
}
