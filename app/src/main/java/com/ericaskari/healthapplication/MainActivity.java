package com.ericaskari.healthapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ericaskari.healthapplication.adapters.HomePageListRecyclerViewAdapter;
import com.ericaskari.healthapplication.interfaces.OnHomePageItemClickListener;
import com.ericaskari.healthapplication.models.HomePageButton;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.modules.medicines.MedicinesActivity;
import com.ericaskari.healthapplication.modules.painhistory.NewPainLogActivity;
import com.ericaskari.healthapplication.modules.painhistory.PainHistoryActivity;
import com.ericaskari.healthapplication.modules.profile.ProfileActivity;
import com.ericaskari.healthapplication.modules.settings.SettingsActivity;
import com.ericaskari.healthapplication.services.AppDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.ericaskari.healthapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnHomePageItemClickListener {

    private ActivityMainBinding activityMainBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //  Get all users from Database
        List<User> users = this.db.userDao().getAll();

        //  Check if first init activity is needed.
        if (users.toArray().length == 0) {
            Intent intent = new Intent(this, Hello.class);
            startActivity(intent);
        } else {
            setContentView(activityMainBinding.getRoot());
        }

        List<HomePageButton> homePageButtons = new ArrayList<>();

        homePageButtons.add(new HomePageButton(R.string.main_activity_pain_history, R.drawable.bg_pain_history));
        homePageButtons.add(new HomePageButton(R.string.main_activity_medicines, R.drawable.bg_capsules));
        homePageButtons.add(new HomePageButton(R.string.main_activity_settings, R.drawable.bg_settings));
        homePageButtons.add(new HomePageButton(R.string.main_activity_own_information, R.drawable.bg_profile));

        HomePageListRecyclerViewAdapter adapter = new HomePageListRecyclerViewAdapter(getApplication(), this, homePageButtons);

        this.activityMainBinding.mainActivityRecyclerView.setAdapter(adapter);
        this.activityMainBinding.mainActivityRecyclerView.setLayoutManager(new GridLayoutManager(null, 2));
        activityMainBinding.addButton.setOnClickListener(this::onAddButtonClick);
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "MainActivity onItemClick: " + position);

        switch (position) {
            case 0:
                onPainHistoryButtonClick(view);
                break;
            case 1:
                onMedicinesButtonClick(view);
                break;
            case 2:
                onSettingsButtonClick(view);
                break;
            case 3:
                onProfileButtonClick(view);
                break;
        }
    }

    private void onPainHistoryButtonClick(View view) {
        Log.d(TAG, "onPainHistoryButtonClick: ");
        startActivity(new Intent(this, PainHistoryActivity.class));
    }

    private void onMedicinesButtonClick(View view) {
        Log.d(TAG, "onMedicinesButtonClick: ");
        startActivity(new Intent(this, MedicinesActivity.class));
    }

    private void onSettingsButtonClick(View view) {
        Log.d(TAG, "onSettingsButtonClick: ");
        startActivity(new Intent(this, SettingsActivity.class));
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