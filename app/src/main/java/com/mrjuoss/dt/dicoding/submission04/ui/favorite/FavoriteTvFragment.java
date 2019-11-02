package com.mrjuoss.dt.dicoding.submission04.ui.favorite;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrjuoss.dt.dicoding.submission04.R;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private FavoriteTvAdapter favoriteTvAdapter;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);

        RecyclerView rvFavMovie = view.findViewById(R.id.recycler_view_fav_movie);

        rvFavMovie.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvFavMovie.setHasFixedSize(true);

        favoriteTvAdapter = new FavoriteTvAdapter(this.getContext());
        rvFavMovie.setAdapter(favoriteTvAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        favoriteViewModel.getFavoritesByType("tv").observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                Log.i("FavoriteViewModel", "onChanged: "+favorites);
                favoriteTvAdapter.setTvFavorites(favorites);
            }
        });
    }

}
