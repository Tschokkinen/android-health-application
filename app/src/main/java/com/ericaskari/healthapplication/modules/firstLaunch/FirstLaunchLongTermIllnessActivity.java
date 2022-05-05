package com.ericaskari.healthapplication.modules.firstLaunch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.ericaskari.healthapplication.MainActivity;
import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Used to save data gathered in previous activity and ask the user if they have a long term illness
 * @author Gavril Tschokkinen
 */
public class FirstLaunchLongTermIllnessActivity extends AppCompatActivity {
    AppDatabase db;

    //View components
    private RadioGroup radioGroup;
    private EditText editTextLongTermIllness;

    //String variables
    private String firstName;
    private String lastName;
    private String height;
    private String weight;
    private String longTermIllness = "";
    private String longTermIllnessDefault = "Ei pitkäaikaissairauksia";

    //Birth date
    private Date birthDate;

    /**
     * onCreate function
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_long_term_illness);

        //Get database
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //Get radiobutton status
        radioButtonSelection();

        //Get views
        editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
    }

    /**
     * Manages all of the button clicks on the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunchLongTermIllness)) {
            saveData(); //Save data to the database

            //Go to verify screen
            Intent intent = new Intent(this, FirstLaunchDoneActivity.class);
            startActivity(intent);
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
    private void saveData() {
        //Log.i("FirstLaunch", "Entered saveData()");

        //Get data from previous activity
        firstName = getIntent().getExtras().getString("First name");
        lastName = getIntent().getExtras().getString("Last name");
        birthDate = (Date) getIntent().getSerializableExtra("Birth date");
        height = getIntent().getExtras().getString("Height");
        weight = getIntent().getExtras().getString("Weight");

        longTermIllness = editTextLongTermIllness.getText().toString(); //Get long term illness
        //If user doesn't have any long term illnesses assign a default value here
        if(longTermIllness.isEmpty()) {
            longTermIllness = longTermIllnessDefault;
        }

        //Create a new User object
        User user = new User(firstName, lastName, birthDate, Integer.parseInt(height), Integer.parseInt(weight), longTermIllness);

        //Insert created User in the database
        this.db.userDao().insertAll(user);

        //Log.i("FirstLaunch", "Data saved");
        //Log.i("FirstLaunch", this.db.userDao().getAll().toString());
    }
}