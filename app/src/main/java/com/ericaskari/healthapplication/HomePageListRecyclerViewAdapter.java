package com.ericaskari.healthapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerViewAdapter to handle list view.
 * It will use {@link HomePageButtonListItemViewHolder} to inject data into
 * {@link ActivityMainCardItemBinding}
 * @author Mohammad Askari (Eric)
 */
public class HomePageListRecyclerViewAdapter extends RecyclerView.Adapter<HomePageButtonListItemViewHolder> {

    List<HomePageButton> homePageButtons;
    Context context;
    OnHomePageItemClickListener listener;

    public HomePageListRecyclerViewAdapter(Context context, OnHomePageItemClickListener listener, List<HomePageButton> homePageButtons) {
        this.homePageButtons = homePageButtons;
        this.context = context;
        this.listener = listener;
    }

    /**
     * Create ViewHolder for list items
     */
    @NonNull
    @Override
    public HomePageButtonListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ActivityMainCardItemBinding binding = ActivityMainCardItemBinding.inflate(layoutInflater, parent, false);

        return new HomePageButtonListItemViewHolder(binding);
    }

    /**
     * Inject data into list items.
     */
    @Override
    public void onBindViewHolder(HomePageButtonListItemViewHolder holder, int position) {
        holder.setViewHolderData(homePageButtons.get(position), position, this.listener);
    }

    @Override
    public int getItemCount() {
        return homePageButtons.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
