package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Gavril Tschokkinen
 * Used to indicate that user data on FirstLaunch activity has been saved successfully.
 */

public class FirstLaunchDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_done);
    }

    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunchFinish)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}