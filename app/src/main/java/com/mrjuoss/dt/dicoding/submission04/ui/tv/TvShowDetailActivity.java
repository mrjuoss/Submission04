package com.mrjuoss.dt.dicoding.submission04.ui.tv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.model.tv.ResultsItem;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.ui.movie.MovieDetailActivity;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

public class TvShowDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private FavoriteViewModel mFavoriteViewModel;

    private ProgressBar progressBarDetailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        progressBarDetailTv = findViewById(R.id.progress_bar_detail_tv);
        ImageView imageDetailTv = findViewById(R.id.image_detail_poster_tv);
        TextView textDetailTitleTv = findViewById(R.id.text_detail_title_tv);
        TextView textDetailRatingTv = findViewById(R.id.text_detail_rating_tv);
        TextView textDetailReleaseTv = findViewById(R.id.text_detail_release_tv);
        TextView textDetailOverviewTv = findViewById(R.id.text_detail_overview_tv);

        progressBarDetailTv.setVisibility(View.VISIBLE);

        final ResultsItem tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

        String rating = Double.toString(tvShow.getVoteAverage());
        String url_poster = "https://image.tmdb.org/t/p/w185"+ tvShow.getPosterPath();

        textDetailTitleTv.setText(tvShow.getName());
        textDetailRatingTv.setText(rating);
        textDetailReleaseTv.setText(tvShow.getFirstAirDate());
        textDetailOverviewTv.setText(tvShow.getOverview());

        Glide.with(TvShowDetailActivity.this)
                .load(url_poster)
                .placeholder(R.color.colorPrimary)
                .dontAnimate()
                .into(imageDetailTv);

        progressBarDetailTv.setVisibility(View.INVISIBLE);

        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        final Button favTvButon = findViewById(R.id.button_favorite_tv);
        favTvButon.setTag("add");
        favTvButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Favorite mFavorite = new Favorite(tvShow.getId(), tvShow.getName(), tvShow.getOverview(), tvShow.getFirstAirDate(), tvShow.getPosterPath(), tvShow.getBackdropPath(), "tv");

                if (favTvButon.getTag().toString() == "add") {
                    mFavoriteViewModel.insert(mFavorite);
                    Toast.makeText(getApplicationContext(), "Berhasil Simpan Data", Toast.LENGTH_SHORT).show();
                    favTvButon.setTag("remove");
                    favTvButon.setText(R.string.remove_favorite);
                } else {

                    Toast.makeText(getApplicationContext(), "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
                    favTvButon.setTag("add");
                    favTvButon.setText(R.string.add_favorite);
                }
            }
        });
    }
}
