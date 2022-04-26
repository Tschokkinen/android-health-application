package com.ericaskari.healthapplication.viewholders;

import androidx.recyclerview.widget.RecyclerView;

import com.ericaskari.healthapplication.adapters.PainLogListRecyclerViewAdapter;
import com.ericaskari.healthapplication.databinding.PainHistoryListItemBinding;
import com.ericaskari.healthapplication.models.PainLog;

import java.text.DateFormat;
import java.util.Locale;

/**
 * ViewHolder to inject data to {@link PainHistoryListItemBinding}
 * Adapter {@link PainLogListRecyclerViewAdapter} will use this class to implement view of each list item.
 */
public class PainHistoryListItemViewHolder extends RecyclerView.ViewHolder {
    private final PainHistoryListItemBinding binding;

    public PainHistoryListItemViewHolder(PainHistoryListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Here we update UI values.
     */
    public void setViewHolderData(PainLog painLog) {
        this.binding.title.setText(painLog.bodyPart);
        this.binding.description.setText(painLog.description);

        Locale loc = new Locale("fi", "FI");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
        String date = dateFormat.format(painLog.createdAt);

        this.binding.addedDate.setText(date);
    }
}