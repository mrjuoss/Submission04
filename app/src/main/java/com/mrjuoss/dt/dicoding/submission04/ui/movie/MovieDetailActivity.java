package com.mrjuoss.dt.dicoding.submission04.ui.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.model.movie.ResultsItem;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    public static final String EXTRA_MOVIE = "extra_movie";

    ProgressBar progressBarDetailMovie;
    ImageView imageDetailPosterMovie;
    TextView textDetailTitleMovie;
    TextView textDetailRatingMovie;
    TextView textDetailreleaseMovie;
    TextView textDetailOverviewMovie;
    private Button buttonFavorite;

    private FavoriteViewModel favoriteViewModel;

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

        buttonFavorite.setOnClickListener(this);

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
        ResultsItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (v.getId() == R.id.button_favorite_movie) {
            int favorite_id = movie.getId();
            String title = movie.getTitle();
            String overview = movie.getOverview();
            String releaseDate = movie.getReleaseDate();
            String posterPath = movie.getPosterPath();
            String backdropPath = movie.getBackdropPath();
            String favoriteType = "movie";

            Favorite data = new Favorite(favorite_id, title, overview, releaseDate, posterPath, backdropPath, favoriteType);
            // Gagal (Null Reference)
            favoriteViewModel.insert(data);

            Toast.makeText(this, "Success Add Favorite Movie", Toast.LENGTH_SHORT).show();

            buttonFavorite.setText("Remove");
        } else {
            buttonFavorite.setText("Remove Favorite");
        }


    }
}
