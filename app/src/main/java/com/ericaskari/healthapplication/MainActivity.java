package com.ericaskari.healthapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ericaskari.healthapplication.adapters.HomePageListRecyclerViewAdapter;
import com.ericaskari.healthapplication.interfaces.OnHomePageItemClickListener;
import com.ericaskari.healthapplication.models.HomePageButton;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.modules.firstLaunch.FirstLaunchHelloActivity;
import com.ericaskari.healthapplication.modules.medicines.MedicinesActivity;
import com.ericaskari.healthapplication.modules.painhistory.NewPainLogActivity;
import com.ericaskari.healthapplication.modules.painhistory.PainHistoryActivity;
import com.ericaskari.healthapplication.modules.profile.ProfileActivity;
import com.ericaskari.healthapplication.modules.settings.SettingsActivity;
import com.ericaskari.healthapplication.services.AppDatabase;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;

import com.ericaskari.healthapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Welcome to entrypoint. Great things happen from here. Enjoy :)
 * At the start of the application it can go to first launch activity to get some basic user data
 * or it can continue to homePage
 *
 * @author Mohammad Askari (Eric)
 */
public class MainActivity extends AppCompatActivity implements OnHomePageItemClickListener {
    private ActivityMainBinding activityMainBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Get View instance
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        //  Get Db instance
        this.db = AppDatabase.getInstance(getApplicationContext());

//        activityMainBinding.addButton.getBackground().mutate().setTint(ContextCompat.getColor(getApplicationContext(), R.color.purple_200));
        activityMainBinding.addButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
        activityMainBinding.addButton.setRippleColor(getResources().getColor(R.color.purple_700));
        //  Get all users from Database
        List<User> users = this.db.userDao().getAll();

        //  Check if first init activity is needed.
        boolean isFirstLaunchNeeded = users.toArray().length == 0;

        if (isFirstLaunchNeeded) {
            Intent intent = new Intent(this, FirstLaunchHelloActivity.class);
            startActivity(intent);

        } else {
            setContentView(activityMainBinding.getRoot());
        }


        //  Create ButtonList for home page
        List<HomePageButton> homePageButtons = new ArrayList<>();

        //  Add Buttons
        homePageButtons.add(new HomePageButton(R.string.main_activity_pain_history, R.drawable.bg_pain_history));
        homePageButtons.add(new HomePageButton(R.string.main_activity_medicines, R.drawable.bg_capsules));
        homePageButtons.add(new HomePageButton(R.string.main_activity_settings, R.drawable.bg_settings));
        homePageButtons.add(new HomePageButton(R.string.main_activity_own_information, R.drawable.bg_profile));

        //  Create adapter. pass this class as listener since it has implemented OnHomePageItemClickListener interface
        HomePageListRecyclerViewAdapter adapter = new HomePageListRecyclerViewAdapter(getApplication(), this, homePageButtons);

        //  Set recycleView adapter
        this.activityMainBinding.mainActivityRecyclerView.setAdapter(adapter);

        //  Set grid layout with two columns
        this.activityMainBinding.mainActivityRecyclerView.setLayoutManager(new GridLayoutManager(null, 2));

        //  Add click listener for floating add button
        activityMainBinding.addButton.setOnClickListener(this::onAddButtonClick);
    }


    /**
     * Handle button clicks from {@link HomePageListRecyclerViewAdapter}
     * @param view clickListener view property
     * @param position index in list of home page buttons
     */
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

    /**
     * Handle button click. called from {@see onItemClick}
     */
    private void onPainHistoryButtonClick(View view) {
        Log.d(TAG, "onPainHistoryButtonClick: ");
        startActivity(new Intent(this, PainHistoryActivity.class));
    }

    /**
     * Handle button click. called from {@see onItemClick}
     */
    private void onMedicinesButtonClick(View view) {
        Log.d(TAG, "onMedicinesButtonClick: ");
        startActivity(new Intent(this, MedicinesActivity.class));
    }

    /**
     * Handle button click. called from {@see onItemClick}
     */
    private void onSettingsButtonClick(View view) {
        Log.d(TAG, "onSettingsButtonClick: ");
        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * Handle button click. called from {@see onItemClick}
     */
    private void onProfileButtonClick(View view) {
        Log.d(TAG, "onProfileButtonClick: ");
        startActivity(new Intent(this, ProfileActivity.class));
    }

    /**
     * Handle button click. called from {@see onItemClick}
     */
    private void onAddButtonClick(View view) {
        Log.d(TAG, "onAddButtonClick: ");
        startActivity(new Intent(this, NewPainLogActivity.class));
    }
}