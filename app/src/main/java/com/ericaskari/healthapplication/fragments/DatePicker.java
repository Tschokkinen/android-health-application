package com.ericaskari.healthapplication.fragments;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * DatePicker popup. It will simply overwrite onCreateDialog and returns a DatePicker.
 *
 * @author Mohammad Askari (Eric)
 */
public class DatePicker extends DialogFragment {
    int year;
    int month;
    int day;

    public DatePicker(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        this.year = cal.get(Calendar.YEAR);;
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateDialog: " + year + " " + month + " " + day);

        return new DatePickerDialog(
                getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(),
                year,
                month,
                day
        );
    }

}