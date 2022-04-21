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

import com.ericaskari.healthapplication.daos.UserDao;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Date;

public class FirstLaunch extends AppCompatActivity {
    private RadioGroup radioGroup;
    //private EditText editTextLongTermIllness;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_hello);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").build();

        //editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
        radioGroup = findViewById(R.id.radioGroup);
        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        editTextAge = findViewById(R.id.age);
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);
    }

    public void buttonManager(View v) {
        if(v == findViewById(R.id.button01)){
            setContentView(R.layout.activity_first_launch);
        } else if(v == findViewById(R.id.button02)) {
            saveData();
            setContentView(R.layout.activity_first_launch_done);
        } else if(v == findViewById(R.id.button03)) {
            setContentView(R.layout.activity_first_launch);
        } else if(v == findViewById(R.id.button04)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(v == findViewById(R.id.radioGroup)){
            Log.i("Radio", "Radio");
            //radioButtonSelection();
        }
    }

    private void saveData() {
        /*
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        Date age = (Date) editTextAge.getText();
        String height = editTextHeight.getText().toString();
        String weight = editTextWeight.getText().toString();

        AsyncTask.execute(() -> {
            UserDao userDao =
                   User user = new User(firstName, lastName, age, height, weight);
            userDao.insertAll(user);
        }
         */
    }

    /*
    private void radioButtonSelection(){
        int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
        RadioButton selection = findViewById(selectedRadioButton);
        Log.i("Radio", "Radio button selection");
        switch (selectedRadioButton) {
            case R.id.radioYes:
                editTextLongTermIllness.setVisibility(View.VISIBLE);
                Log.d("Radio", "Radio yes");
                break;
            case R.id.radioNo:
                editTextLongTermIllness.setVisibility(View.INVISIBLE);
            }
    }
    */

}