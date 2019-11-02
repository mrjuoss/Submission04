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
import com.mrjuoss.dt.dicoding.submission04.adapter.MovieAdapter;
import com.mrjuoss.dt.dicoding.submission04.room.Favorite;
import com.mrjuoss.dt.dicoding.submission04.viewmodel.FavoriteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    FavoriteViewModel favoriteViewModel;
    FavoriteMovieAdapter favoriteMovieAdapter;


    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);

        RecyclerView rvFavMovie = view.findViewById(R.id.recycler_view_fav_movie);

        rvFavMovie.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvFavMovie.setHasFixedSize(true);

        favoriteMovieAdapter = new FavoriteMovieAdapter(this.getContext());
        rvFavMovie.setAdapter(favoriteMovieAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);

        favoriteViewModel.getFavoritesByType("movie").observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                Log.i("FavoriteViewModel", "onChanged: "+favorites);
                favoriteMovieAdapter.setMovieFavorites(favorites);
            }
        });
    }
}
