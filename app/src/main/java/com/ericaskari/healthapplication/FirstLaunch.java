package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Date;

/**
 * @author Gavril Tschokkinen
 */

public class FirstLaunch extends AppCompatActivity {
    AppDatabase db;
    private RadioGroup radioGroup;
    private EditText editTextLongTermIllness;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    private int date = 0;
    private int month = 0;
    private int year = 0;
    private String longTermIllness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch);

        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        radioButtonSelection();

        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        editTextAge = findViewById(R.id.age);
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);

    }

    public void buttonManager(View v) {
        if(v == findViewById(R.id.nextButtonFirstLaunch)) {
            saveData();
        } else if (v == findViewById(R.id.radioGroup)) {
            radioButtonSelection();
        }
    }

    private void saveData() {
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        String birthDate = (((EditText) findViewById(R.id.age)).getText().toString());
        String height = ((EditText) findViewById(R.id.height)).getText().toString();
        String weight = ((EditText) findViewById(R.id.weight)).getText().toString();

        longTermIllness = ((EditText) findViewById(R.id.editTextLongTermIllness)).getText().toString();

        if(!birthDate.isEmpty()){
            String splitBirthDate[] = birthDate.split("/");
            date = Integer.parseInt(splitBirthDate[0]);
            month = Integer.parseInt(splitBirthDate[1]);
            year = Integer.parseInt(splitBirthDate[2]);
        }

        if(longTermIllness.isEmpty()){
            longTermIllness = "Ei pitkäaikaissairauksia.";
        }

        if(!firstName.isEmpty() && !lastName.isEmpty() && !height.isEmpty() && !weight.isEmpty()) {
            AsyncTask.execute(() -> {
                User user = new User(firstName, lastName, new Date(year, month, date), Integer.parseInt(height), Integer.parseInt(weight), longTermIllness);
                this.db.userDao().insertAll(user);
                Log.i("FirstLaunch", "Data saved");
                Intent intent = new Intent(this, FirstLaunchDone.class);
                startActivity(intent);
            });
        } else {
            Log.d("FirstLaunch", "A required field is null");
        }
    }

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
            }
        });
    }
}