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

public class FavoriteTvAdapter extends RecyclerView.Adapter<FavoriteTvAdapter.FavoriteTvViewHolder> {

    private Context mContext;
    private final LayoutInflater layoutInflater;
    private List<Favorite> mTvFavorites;

    public FavoriteTvAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setTvFavorites(List<Favorite> favorites) {
        mTvFavorites = favorites;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteTvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_card_view_movie, parent, false);
        FavoriteTvViewHolder viewHolder = new FavoriteTvViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvViewHolder holder, int position) {
        holder.bind(mTvFavorites.get(position));
    }

    @Override
    public int getItemCount()
    {
        if (mTvFavorites != null) {
            return mTvFavorites.size();
        } else {
            return 0;
        }
    }

    public class FavoriteTvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imagePosterTv;
        TextView textNameTv, textReleaseTv, textOverviewTv;

        public FavoriteTvViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePosterTv = itemView.findViewById(R.id.image_poster_tv);
            textNameTv = itemView.findViewById(R.id.text_title_tv);
            textReleaseTv = itemView.findViewById(R.id.text_release_tv);
            textOverviewTv = itemView.findViewById(R.id.text_overview_tv);

            itemView.setOnClickListener(this);
        }

        public void bind(Favorite favorite) {
            String url_poster_tv = "https://image.tmdb.org/t/p/w185" + favorite.getPosterPath();

            textNameTv.setText(favorite.getTitle());
            textReleaseTv.setText(favorite.getReleaseDate());
            textOverviewTv.setText(favorite.getOverview());

            Glide.with(itemView.getContext())
                    .load(url_poster_tv)
                    .placeholder(R.color.colorPrimary)
                    .dontAnimate().into(imagePosterTv);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
