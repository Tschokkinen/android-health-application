package com.ericaskari.healthapplication.modules.firstLaunch;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.fragments.DatePicker;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;
import com.ericaskari.healthapplication.validators.UserModelValidation;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Gavril Tschokkinen
 * Activity for user data collection during the first launch of the application.
 */

public class FirstLaunchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    AppDatabase db;

    //View components
    private RadioGroup radioGroup;
    private EditText editTextLongTermIllness;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private TextView textViewBirthDate;
    private EditText editTextHeight;
    private EditText editTextWeight;

    //String variables
    private String firstName;
    private String lastName;
    private String birthDateText;
    private String height;
    private String weight;
    private String longTermIllness;
    private String longTermIllnessDefault = "Ei pitkäaikaissairauksia";

    //Birth date
    private Date birthDate;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);

        //Get database
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //Get radiobutton status
        radioButtonSelection();

        //Create new Date() object
        birthDate = new Date();

        //Get all EditTexts and TextViews
        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        textViewBirthDate = findViewById(R.id.textViewBirthDate);
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
        editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
    }

    /**
     * Manages all of the button clicks on the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunch)) {
            gatherData();
        } else if (v == findViewById(R.id.radioGroup)) {
            radioButtonSelection();
        } else if(v == findViewById(R.id.buttonPickBirthdateFirstLaunch)) {
            Date currentTime = Calendar.getInstance().getTime(); //Get time and date of log
            showDatePicker(currentTime);
        }
    }

    /**
     * Opens Date picker
     */
    private void showDatePicker(Date defaultDate) {
        Log.i("DatePicker", "showDatePicker()");
        //Default date for date picker
        DatePicker datePicker = new DatePicker(defaultDate);
        //Show date picker
        datePicker.show(getSupportFragmentManager(), "DATE PICK");
    }

    /**
     * Gather the user input and send it to saveData() if everything checks out.
     */
    //Gather data from the input fields and save data if everything checks out.
    private void gatherData() {
        //Get values from view components and assign values to corresponding variables
        firstName = editTextFirstName.getText().toString();
        lastName = editTextLastName.getText().toString();
        birthDateText = textViewBirthDate.getText().toString();
        height = editTextHeight.getText().toString();
        weight = editTextWeight.getText().toString();
        longTermIllness = editTextLongTermIllness.getText().toString();

        //If user doesn't have any long term illnesses assign a default value here
        if(longTermIllness.isEmpty()) {
            longTermIllness = longTermIllnessDefault;
        }

        UserModelValidation userModelValidation = new UserModelValidation(
                editTextFirstName,
                editTextLastName,
                textViewBirthDate,
                editTextHeight,
                editTextWeight
        );

        //If none of the required fields are not empty, saveData()
        if(userModelValidation.validate()) {
            saveData();
        }
    }

    /**
     * Saves the user input to the database
     */
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
     *
     * @param datePicker
     * @param year
     * @param month
     * @param dayOfMonth
     */
    //Modified version of Mohammad's code written in ProfileEditActivity
    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Log.d(TAG, "onDateSet: : " + year + " " + month + " " + dayOfMonth);

        //Get year, month and date
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, dayOfMonth);

        birthDate = calendar.getTime();

        textViewBirthDate.setText(
                new StringBuilder()
                        .append(dayOfMonth)
                        .append(".")
                        .append(month)
                        .append(".")
                        .append(year)
                        .toString());
    }
}