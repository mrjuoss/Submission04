package com.mrjuoss.dt.dicoding.submission04.ui.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;

import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {

    private Context mContext;
    private final LayoutInflater layoutInflater;
    private List<Favorite> mMovieFavorites;

    public FavoriteMovieAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setMovieFavorites(List<Favorite> favorites) {
        mMovieFavorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_card_view_movie, parent, false);
        FavoriteMovieViewHolder viewHolder = new FavoriteMovieViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieViewHolder holder, int position) {
        holder.bind(mMovieFavorites.get(position));
    }

    @Override
    public int getItemCount() {
        if (mMovieFavorites != null) {
            return mMovieFavorites.size();
        } else {
            return 0;
        }

    }

    public class FavoriteMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPosterMovie;
        TextView textTitleMovie, textReleaseMovie, textOverviewMovie;
        public FavoriteMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPosterMovie = itemView.findViewById(R.id.image_poster);
            textTitleMovie = itemView.findViewById(R.id.text_title);
            textReleaseMovie = itemView.findViewById(R.id.text_release);
            textOverviewMovie = itemView.findViewById(R.id.text_overview_movie);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bind(Favorite favorite) {
            String url_poster = "https://image.tmdb.org/t/p/w185"+ favorite.getPosterPath();

            textTitleMovie.setText(favorite.getTitle());
            textReleaseMovie.setText(favorite.getReleaseDate());
            textOverviewMovie.setText(favorite.getOverview());

            Glide.with(itemView.getContext())
                    .load(url_poster)
                    .into(imgPosterMovie);
        }
    }
}
