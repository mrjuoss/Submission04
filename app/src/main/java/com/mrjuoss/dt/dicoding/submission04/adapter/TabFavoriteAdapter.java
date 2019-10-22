package com.mrjuoss.dt.dicoding.submission04.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mrjuoss.dt.dicoding.submission04.ui.favorite.FavoriteMovieFragment;
import com.mrjuoss.dt.dicoding.submission04.ui.favorite.FavoriteTvFragment;

public class TabFavoriteAdapter extends FragmentPagerAdapter {

    private int tabCountFavorite;

    public TabFavoriteAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm, numberOfTabs);
        this.tabCountFavorite = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCountFavorite;
    }
}
