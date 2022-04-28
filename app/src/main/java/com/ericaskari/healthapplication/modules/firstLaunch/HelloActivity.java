package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ericaskari.healthapplication.R;

/**
 * @author Gavril Tschokkinen
 * The initial activity run when the application is booted for the first time.
 */

public class HelloActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v){
        if(v == findViewById(R.id.nextButtonHello)){
            Intent intent = new Intent(this, FirstLaunchActivity.class);
            startActivity(intent);
        }
    }
}