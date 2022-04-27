package com.ericaskari.healthapplication.viewholders;


import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ericaskari.healthapplication.databinding.ActivityMainCardItemBinding;
import com.ericaskari.healthapplication.interfaces.OnHomePageItemClickListener;
import com.ericaskari.healthapplication.models.HomePageButton;

public class HomePageButtonListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ActivityMainCardItemBinding binding;

    @Nullable
    private OnHomePageItemClickListener listener = null;

    @Nullable
    private Integer position = null;

    public HomePageButtonListItemViewHolder(ActivityMainCardItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(this);
    }

    /**
     * Here we update UI values.
     */
    public void setViewHolderData(HomePageButton homePageButton, int position, OnHomePageItemClickListener listener) {
        //  Set UI Text
        this.binding.homeCardText.setText(binding.getRoot().getResources().getString(homePageButton.getTextResourceId()));
        //  Set UI Image
        this.binding.homeCardImage.setImageResource(homePageButton.getImageResourceId());
        //  Save listener instance
        this.listener = listener;
        //  Save position to pass into click listener
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "HomePageButtonListItemViewHolder onClick: " + this.position);
        if (this.listener != null) {
            this.listener.onItemClick(v, this.position);
        }
    }
}