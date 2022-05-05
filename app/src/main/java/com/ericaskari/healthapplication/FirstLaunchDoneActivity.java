package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Gavril Tschokkinen
 * Used to indicate that user data has been saved successfully.
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