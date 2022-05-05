package com.ericaskari.healthapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ericaskari.healthapplication.databinding.PainHistoryListItemBinding;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.viewholders.PainHistoryListItemViewHolder;

import java.util.List;

/**
 * RecyclerViewAdapter to handle list view.
 * @author Mohammad Askari (Eric)
 * It will use {@link PainHistoryListItemViewHolder} to inject data into
 * {@link PainHistoryListItemBinding}
 */
public class PainLogListRecyclerViewAdapter extends RecyclerView.Adapter<PainHistoryListItemViewHolder> {

    List<PainLog> list;
    Context context;

    public PainLogListRecyclerViewAdapter(List<PainLog> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * Create ViewHolder for list items
     */
    @NonNull
    @Override
    public PainHistoryListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PainHistoryListItemBinding binding = PainHistoryListItemBinding.inflate(layoutInflater, parent, false);
        return new PainHistoryListItemViewHolder(binding);
    }

    /**
     * Inject data into list items.
     */
    @Override
    public void onBindViewHolder(PainHistoryListItemViewHolder holder, int position) {
        holder.setViewHolderData(list.get(position), context.getResources());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insert(int position, PainLog data) {
        list.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(PainLog data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

}
