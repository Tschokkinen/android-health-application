package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ericaskari.healthapplication.MainActivity;
import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Date;

/**
 * Used to indicate that user data has been saved successfully.
 * @author Gavril Tschokkinen
 */

public class FirstLaunchDoneActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_done);
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunchFinish)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}