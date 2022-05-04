package com.ericaskari.healthapplication.modules.profile;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.ProfileEditBinding;
import com.ericaskari.healthapplication.fragments.DatePicker;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;
import com.ericaskari.healthapplication.validators.UserModelValidation;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mohammad Askari (Eric)
 */
public class ProfileEditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ProfileEditBinding binding;
    private AppDatabase db;
    private User dbUser;
    private User modifiedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ProfileEditBinding.inflate(getLayoutInflater());
        this.db = AppDatabase.getInstance(getApplicationContext());

        setContentView(binding.getRoot());

        AsyncTask.execute(() -> {
            //  Get User
            this.dbUser = this.db.userDao().getAll().get(0);
            this.modifiedUser = this.db.userDao().getAll().get(0);

            //  Fill fields with current data
            this.fillUiDefaultValues(dbUser);

            //  Activate click listeners
            this.binding.saveButton.setOnClickListener(this::onSaveButtonClick);
            this.binding.discardButton.setOnClickListener(this::onDiscardButtonClick);
            this.binding.birthdatePickButton.setOnClickListener(this::onBirthdatePickButtonClick);

        });
    }

    private void onSaveButtonClick(View view) {
        Log.d(TAG, "onSaveButtonClick: " + this);

        UserModelValidation userModelValidation = new UserModelValidation(
                binding.firstnameValue,
                binding.lastNameValue,
                binding.birthdateValue,
                binding.heightValue,
                binding.weightValue
        );

        //  Stop if it's not valid form
        if (!userModelValidation.validate()) {
            return;
        }

        modifiedUser.firstName = this.binding.firstnameValue.getText().toString();
        modifiedUser.lastName = this.binding.lastNameValue.getText().toString();
        modifiedUser.weight = Integer.parseInt(this.binding.weightValue.getText().toString());
        modifiedUser.height = Integer.parseInt(this.binding.heightValue.getText().toString());
        //  birthDate should be set whenever date picker is closed by clicking "OK"
        this.db.userDao().update(modifiedUser);
        this.onBackPressed();
    }

    private void onDiscardButtonClick(View view) {
        onBackPressed();
    }

    private void onBirthdatePickButtonClick(View view) {
        showDatePicker(modifiedUser.birthDate);
    }


    /**
     * Initial field filling
     */
    private void fillUiDefaultValues(User user) {
        this.binding.firstnameValue.setText(user.firstName);
        this.binding.lastNameValue.setText(user.lastName);
        this.binding.weightValue.setText(String.valueOf(user.weight));
        this.binding.heightValue.setText(String.valueOf(user.height));
        this.updateBirthdateUiValue(user.birthDate);
        this.binding.longTermIllnessValue.setText(user.longTermIllness);
    }

    /**
     * Opens Date picker
     */
    private void showDatePicker(Date defaultDate) {
        //  Default date for date picker
        DatePicker datePicker = new DatePicker(defaultDate);
        //  Show date picker
        datePicker.show(getSupportFragmentManager(), "DATE PICK");
    }

    /**
     * When user clicks OK on datePicker
     */
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: : " + year + " " + month + " " + dayOfMonth);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, dayOfMonth);

        this.modifiedUser.birthDate = calendar.getTime();

        this.updateBirthdateUiValue(this.modifiedUser.birthDate);
    }


    /**
     * Update UI value of date
     */
    private void updateBirthdateUiValue(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        //  Since month starts from zero we need to add one to show in UI
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DATE);

        Log.d(TAG, "updateBirthdateUiValue: : " + year + " " + month + " " + dayOfMonth);

        this.binding.birthdateValue.setText(
                new StringBuilder()
                        .append(dayOfMonth)
                        .append(".")
                        .append(month)
                        .append(".")
                        .append(year)
                        .toString()
        );
    }
}
