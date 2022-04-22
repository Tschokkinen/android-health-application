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
    AppDatabase db;
    private RadioGroup radioGroup;
    private EditText editTextLongTermIllness;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_hello);

        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").build();

        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        editTextAge = findViewById(R.id.age);
        editTextHeight = findViewById(R.id.height);
        editTextWeight = findViewById(R.id.weight);

    }

    public void buttonManager(View v) {
        if(v == findViewById(R.id.button01)){
            setContentView(R.layout.activity_first_launch);
            radioButtonSelection();

        } else if(v == findViewById(R.id.button02)) {
            saveData();
            //setContentView(R.layout.activity_first_launch_done);

        } else if(v == findViewById(R.id.button03)) {
            setContentView(R.layout.activity_first_launch);
            radioButtonSelection();

        } else if(v == findViewById(R.id.button04)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if(v == findViewById(R.id.radioGroup)){
            Log.i("Radio", "Radio");
            //radioButtonSelection();
        }
    }

    private void saveData() {
        String firstName = ((EditText) findViewById(R.id.firstName)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastName)).getText().toString();
        //Date birthDate = new Date(((EditText) findViewById(R.id.age)).getText().toString());
        String height = ((EditText) findViewById(R.id.height)).getText().toString();
        String weight = ((EditText) findViewById(R.id.weight)).getText().toString();


        AsyncTask.execute(() -> {
            User user = new User(firstName, lastName, new Date(1984, 12, 14), Integer.parseInt(height), Integer.parseInt(weight), "Ei");
            this.db.userDao().insertAll(user);
            setContentView(R.layout.activity_first_launch_done);
        });
    }

    private void radioButtonSelection(){
        //  findViewById should be where button is visible otherwise it will return null.
        radioGroup = findViewById(R.id.radioGroup);
        editTextLongTermIllness = findViewById(R.id.editTextLongTermIllness);
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            Log.i("Radio", "Radio button selection");
            switch (i) {
                case R.id.radioYes:
                    editTextLongTermIllness.setVisibility(View.VISIBLE);
                    Log.d("Radio", "Radio yes");
                    break;
                case R.id.radioNo:
                    editTextLongTermIllness.setVisibility(View.INVISIBLE);
            }
        });
    }
}