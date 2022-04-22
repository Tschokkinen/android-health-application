package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        } else if(v == findViewById(R.id.backButtonFirstLaunch)) {
            Intent intent = new Intent(this, FirstLaunch.class);
            startActivity(intent);
        }
    }
}