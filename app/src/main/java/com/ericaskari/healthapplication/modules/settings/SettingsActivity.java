package com.ericaskari.healthapplication.modules.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.SettingsBinding;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.modules.firstLaunch.FirstLaunchUserDataActivity;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.List;

/**
 * @author Mohammad Askari (Eric)
 */
public class SettingsActivity extends AppCompatActivity {
    String TAG = "SettingsActivity";

    //  View reference
    private SettingsBinding settingsActivityBinding;

    //  Database reference
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Save view reference
        settingsActivityBinding = SettingsBinding.inflate(getLayoutInflater());

        //  Save Database reference
        this.db = AppDatabase.getInstance(getApplicationContext());

        //  Show User the view
        setContentView(settingsActivityBinding.getRoot());

        //  Add needed clickListeners
        settingsActivityBinding.deleteData.setOnClickListener(this::onDeleteDataButtonClick);
    }

    private void onDeleteDataButtonClick(View view) {
        Log.d(TAG, "onDeleteDataButtonClick: ");

        //  Get Users
        List<User> users = this.db.userDao().getAll();
        List<PainLog> painLogList = this.db.painLogDao().getAll();

        //  Delete PainLogs
        for (PainLog painLog : painLogList) {
            this.db.painLogDao().delete(painLog);
        }

        //  Delete Users
        for (User user : users) {
            this.db.userDao().delete(user);
        }

        //  Go to first launch
        Intent intent = new Intent(this, FirstLaunchUserDataActivity.class);
        startActivity(intent);
    }
}
