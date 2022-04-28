package com.ericaskari.healthapplication.viewholders;


import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ericaskari.healthapplication.databinding.ActivityMainCardItemBinding;
import com.ericaskari.healthapplication.interfaces.OnHomePageItemClickListener;
import com.ericaskari.healthapplication.models.HomePageButton;

/**
 * @author Mohammad Askari (Eric)
 */
public class HomePageButtonListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //  view reference
    private final ActivityMainCardItemBinding binding;

    //  click listener reference
    @Nullable
    private OnHomePageItemClickListener listener = null;

    //  list index
    @Nullable
    private Integer position = null;

    /**
     * @param binding view reference
     */
    public HomePageButtonListItemViewHolder(ActivityMainCardItemBinding binding) {
        //  ViewHolder constructor
        super(binding.getRoot());

        //  Saving View reference to set UI values later on.
        this.binding = binding;

        //  Adding clickListener and passing current instance because it implemented the View.OnClickListener
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

        //  Validation
        if (this.listener == null || this.position == null) {
            return;
        }

        this.listener.onItemClick(v, this.position);
    }
}