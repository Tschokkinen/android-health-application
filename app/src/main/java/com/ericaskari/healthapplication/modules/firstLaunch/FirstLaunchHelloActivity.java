package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ericaskari.healthapplication.R;

/**
 * The initial activity run when the application is booted for the first time.
 * @author Gavril Tschokkinen
 */

public class FirstLaunchHelloActivity extends AppCompatActivity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_hello);
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v){
        if(v == findViewById(R.id.nextButtonHello)){
            //Go to the next activity
            Intent intent = new Intent(this, FirstLaunchUserDataActivity.class);
            startActivity(intent);
        }
    }
}