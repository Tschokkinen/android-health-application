package com.ericaskari.healthapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.modules.medicines.MedicinesActivity;
import com.ericaskari.healthapplication.modules.painhistory.NewPainLogActivity;
import com.ericaskari.healthapplication.modules.painhistory.PainHistoryActivity;
import com.ericaskari.healthapplication.modules.profile.ProfileActivity;
import com.ericaskari.healthapplication.modules.settings.SettingsActivity;
import com.ericaskari.healthapplication.services.AppDatabase;

import androidx.appcompat.app.AppCompatActivity;

import androidx.room.Room;

import com.ericaskari.healthapplication.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        AsyncTask.execute(() -> {
            //  Get all users from Database
            List<User> users = this.db.userDao().getAll();

            //  Check if first init activity is needed.
            if (users.toArray().length == 0) {
                Intent intent = new Intent(this, Hello.class);
                startActivity(intent);
            } else {
                setContentView(activityMainBinding.getRoot());
            }
        });

        activityMainBinding.painHistoryButton.setOnClickListener(this::onPainHistoryButtonClick);
        activityMainBinding.medicinesButton.setOnClickListener(this::onMedicinesButtonClick);
        activityMainBinding.settingsButton.setOnClickListener(this::onSettingsButtonClick);
        activityMainBinding.profileButton.setOnClickListener(this::onProfileButtonClick);
        activityMainBinding.addButton.setOnClickListener(this::onAddButtonClick);
    }

    private void onSettingsButtonClick(View view) {
        Log.d(TAG, "onSettingsButtonClick: ");
        startActivity(new Intent(this, SettingsActivity.class));
    }


    private void onPainHistoryButtonClick(View view) {
        Log.d(TAG, "onPainHistoryButtonClick: ");
        startActivity(new Intent(this, PainHistoryActivity.class));

    }


    private void onMedicinesButtonClick(View view) {
        Log.d(TAG, "onMedicinesButtonClick: ");
        startActivity(new Intent(this, MedicinesActivity.class));

    }


    private void onProfileButtonClick(View view) {
        Log.d(TAG, "onProfileButtonClick: ");
        startActivity(new Intent(this, ProfileActivity.class));

    }


    private void onAddButtonClick(View view) {
        Log.d(TAG, "onAddButtonClick: ");
        startActivity(new Intent(this, NewPainLogActivity.class));

    }
}