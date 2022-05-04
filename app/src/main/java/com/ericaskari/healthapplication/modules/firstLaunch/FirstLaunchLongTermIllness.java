package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;

public class FirstLaunchLongTermIllness extends AppCompatActivity {
    AppDatabase db;

    //View components
    private RadioGroup radioGroup;
    private EditText editTextLongTermIllness;

    private String longTermIllness;
    private String longTermIllnessDefault = "Ei pitkäaikaissairauksia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_long_term_illness);

        String val = getIntent().getExtras().getString("First name");
        Log.i("Name", val);

        //Get radiobutton status
        radioButtonSelection();

        editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
    }

    /**
     * Manages all of the button clicks on the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunchLongTermIllness)) {
            //gatherData();
            //If user doesn't have any long term illnesses assign a default value here
            if(longTermIllness.isEmpty()) {
                longTermIllness = longTermIllnessDefault;
            }
        } else if (v == findViewById(R.id.radioGroup)) {
            radioButtonSelection();
        }
    }

    /**
     * See which radio button is being selected
     */
    //Manages radio button selection. Yes displays a field for long term illness.
    private void radioButtonSelection(){
        radioGroup = findViewById(R.id.radioGroup);
        editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            //Log.i("Radio", "Radio button selection");
            switch (i) {
                case R.id.radioYes:
                    editTextLongTermIllness.setVisibility(View.VISIBLE);
                    //Log.d("Radio", "Radio yes");
                    break;
                case R.id.radioNo:
                    editTextLongTermIllness.setVisibility(View.INVISIBLE);
                    //Log.d("Radio", "Radio no");
            }
        });
    }

    /**
     * Saves the user input to the database
     */
    /*
    private void saveData() {
        //Create a new User object
        User user = new User(firstName, lastName, birthDate, Integer.parseInt(height), Integer.parseInt(weight), longTermIllness);

        //Inser created User in the database
        this.db.userDao().insertAll(user);

        Log.i("FirstLaunch", "Data saved");

        Log.i("FirstLaunch", this.db.userDao().getAll().toString());

        //Go to verify screen
        Intent intent = new Intent(this, FirstLaunchDoneActivity.class);
        startActivity(intent);
    }

     */
}