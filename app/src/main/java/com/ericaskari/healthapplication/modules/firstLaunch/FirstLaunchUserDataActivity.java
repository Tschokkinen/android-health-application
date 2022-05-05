package com.ericaskari.healthapplication.modules.firstLaunch;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.fragments.DatePicker;
import com.ericaskari.healthapplication.validators.UserModelValidation;

import java.util.Calendar;
import java.util.Date;

/**
 * Activity for user data collection during the first launch of the application.
 * @author Gavril Tschokkinen
 */

public class FirstLaunchUserDataActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    //View components
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

    //Birth date
    private Date birthDate;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_user_data);

        //Create new Date() object
        birthDate = new Date();

        //Get all EditTexts and TextViews
        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        textViewBirthDate = findViewById(R.id.textViewBirthDate);
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
    }

    /**
     * Manages all of the button clicks on the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunchLongTermIllness)) {
            gatherData(); //Gather input data and pass it to the next activity
        } else if(v == findViewById(R.id.buttonPickBirthdateFirstLaunch)) {
            Date currentTime = Calendar.getInstance().getTime(); //Get current time and date
            showDatePicker(currentTime);
        }
    }

    /**
     * Gathers the user input for validation and sends it to next activity if everything checks out.
     */
    //Gather data from the input fields for validation and passes it on if everything checks out.
    private void gatherData() {
        //Get values from view components and assign values to corresponding variables
        firstName = editTextFirstName.getText().toString();
        lastName = editTextLastName.getText().toString();
        birthDateText = textViewBirthDate.getText().toString();
        height = editTextHeight.getText().toString();
        weight = editTextWeight.getText().toString();

        //Check if values are valid and not empty
        UserModelValidation userModelValidation = new UserModelValidation(
                editTextFirstName,
                editTextLastName,
                textViewBirthDate,
                editTextHeight,
                editTextWeight
        );

        //If none of the required fields are not empty, pass the data to the next activity
        if(userModelValidation.validate()) {
            Bundle bundle = new Bundle(); //Data bundle
            bundle.putString("First name", firstName);
            bundle.putString("Last name", lastName);
            bundle.putSerializable("Birth date", birthDate);
            bundle.putString("Height", height);
            bundle.putString("Weight", weight);

            Intent intent = new Intent(this, FirstLaunchLongTermIllnessActivity.class);
            intent.putExtras(bundle); //Pass data bundle to the next activity
            startActivity(intent);
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
     *
     * @param datePicker
     * @param year
     * @param month
     * @param dayOfMonth
     */
    //Very slightly modified version of Mohammad's code written in ProfileEditActivity
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