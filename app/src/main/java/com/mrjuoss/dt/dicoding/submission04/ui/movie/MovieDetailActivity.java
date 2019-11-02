package com.mrjuoss.dt.dicoding.submission04.ui.movie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.model.movie.ResultsItem;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

public class MovieDetailActivity extends AppCompatActivity{

    public static final String EXTRA_MOVIE = "extra_movie";

    private Favorite mFavorite;
    private FavoriteViewModel mFavoriteViewModel;

    ProgressBar progressBarDetailMovie;
    Button buttonFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        progressBarDetailMovie = findViewById(R.id.progress_bar_detail_movie);
        ImageView imageDetailPosterMovie = findViewById(R.id.image_detail_poster_movie);
        TextView textDetailTitleMovie = findViewById(R.id.text_detail_title_movie);
        TextView textDetailRatingMovie = findViewById(R.id.text_detail_rating_movie);
        TextView textDetailreleaseMovie = findViewById(R.id.text_detail_release_movie);
        TextView textDetailOverviewMovie = findViewById(R.id.text_detail_overview_movie);

        progressBarDetailMovie.setVisibility(View.VISIBLE);

        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        final ResultsItem movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

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

        buttonFavorite = findViewById(R.id.button_favorite_movie);
        buttonFavorite.setTag("add");
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavorite = new Favorite(movie.getId(), movie.getTitle(), movie.getOverview(), movie.getReleaseDate(), movie.getPosterPath(), movie.getBackdropPath(), "movie");

                if (buttonFavorite.getTag().toString() == "add") {
                    mFavoriteViewModel.insert(mFavorite);
                    Toast.makeText(getApplicationContext(), "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                    buttonFavorite.setTag("remove");
                    buttonFavorite.setText(R.string.remove_favorite);
                } else {

                    Toast.makeText(getApplicationContext(), "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
                    buttonFavorite.setTag("add");
                    buttonFavorite.setText(R.string.add_favorite);
                }
            }
        });
    }

}
