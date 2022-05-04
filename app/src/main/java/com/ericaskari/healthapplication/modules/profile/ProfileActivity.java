package com.ericaskari.healthapplication.modules.profile;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.databinding.ProfileBinding;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;


/**
 * @author Mohammad Askari (Eric)
 *
 */
public class ProfileActivity extends AppCompatActivity {
    //  View reference
    private ProfileBinding binding;

    //  Database reference
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Save view reference
        this.binding = ProfileBinding.inflate(getLayoutInflater());

        //  Save Database reference
        this.db = AppDatabase.getInstance(getApplicationContext());

        //  Show User the view
        setContentView(binding.getRoot());

        updateUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
        Log.d(TAG, "onResume: ");
    }

    /**
     * Assign custom Navbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_main, menu);
        return true;
    }

    /**
     * Listen to clicks on Custom Navbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_menu_edit) {
            onProfileMenuEditButtonClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onProfileMenuEditButtonClick() {
        Log.d(TAG, "onProfileMenuEditButtonClick: ");
        Intent intent = new Intent(this, ProfileEditActivity.class);
        startActivity(intent);
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

    private void updateUi() {
        //  Get User fro Database to refresh data
        User user = this.db.userDao().getAll().get(0);

        //  Update Ui data
        this.binding.firstnameValue.setText(user.firstName.toString());
        this.binding.lastNameValue.setText(user.lastName.toString());
        this.updateBirthdateUiValue(user.birthDate);
        this.binding.heightValue.setText(String.valueOf(user.height));
        this.binding.weightValue.setText(String.valueOf(user.weight));
        this.binding.longTermIllnessValue.setText(String.valueOf(user.longTermIllness));
    }
}
