package com.mrjuoss.dt.dicoding.submission04;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {

    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener (int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }
    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
