package com.ericaskari.healthapplication.viewholders;

import android.content.res.Resources;

import androidx.recyclerview.widget.RecyclerView;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.adapters.PainLogListRecyclerViewAdapter;
import com.ericaskari.healthapplication.databinding.PainHistoryListItemBinding;
import com.ericaskari.healthapplication.models.PainLog;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * ViewHolder to inject data to {@link PainHistoryListItemBinding}
 * @author Mohammad Askari (Eric)
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
     * @param resources just to get some values from strings.xml
     * @param painLog to get values for UI
     */
    public void setViewHolderData(PainLog painLog, Resources resources) {
        //  Set UI value for title
        this.binding.titleValue.setText(painLog.bodyPart);

        //  Set UI value for description
        this.binding.descriptionValue.setText(painLog.description);

        //  create formatted createdAt date for UI
        LocalDateTime createdAtLocalDateTime = painLog.createdAt.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DateTimeFormatter createdAtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String createdAtFormatted = createdAtLocalDateTime.format(createdAtFormatter);

        //  Set UI value for createdAt date
        this.binding.createdAtValue.setText(createdAtFormatted);

        //  Set UI value for painStrength
        this.binding.painStrengthValue.setText(String.format("%d/10", painLog.painStrength));

        //  Set UI value for painStrength
        this.binding.medicinesTakenText.setText(
                resources.getString(
                        painLog.medicineTaken.equals("")
                                ? R.string.no_medicine_is_taken
                                : R.string.taken_medicine
                )
        );

        this.binding.medicinesTakenValue.setText(painLog.medicineTaken);
        this.binding.howMedicineAffectedValue.setText(painLog.tellAboutYourFeelings);
    }
}