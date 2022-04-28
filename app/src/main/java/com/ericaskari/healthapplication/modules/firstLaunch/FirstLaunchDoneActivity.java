package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ericaskari.healthapplication.MainActivity;
import com.ericaskari.healthapplication.R;

/**
 * @author Gavril Tschokkinen
 * Used to indicate that user data on FirstLaunch activity has been saved successfully.
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