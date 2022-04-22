package com.ericaskari.healthapplication.modules.settings;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ericaskari.healthapplication.FirstLaunch;
import com.ericaskari.healthapplication.databinding.SettingsBinding;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    private SettingsBinding settingsActivityBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsActivityBinding = SettingsBinding.inflate(getLayoutInflater());
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").build();

        setContentView(settingsActivityBinding.getRoot());

        settingsActivityBinding.deleteData.setOnClickListener(this::onDeleteDataButtonClick);
    }

    private void onDeleteDataButtonClick(View view) {
        Log.d(TAG, "onDeleteDataButtonClick: ");
        AsyncTask.execute(() -> {
            List<User> users = this.db.userDao().getAll();

            for (User user : users) {
                this.db.userDao().delete(user);
            }
            
            Intent intent = new Intent(this, FirstLaunch.class);
            startActivity(intent);
        });
    }
}
