package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FirstLaunch extends AppCompatActivity {
    private RadioGroup radioGroup;
    private EditText etLongTermIllness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_launch_hello);

        etLongTermIllness = findViewById(R.id.etLongTermIllness);
        radioGroup = findViewById(R.id.radioGroup);
    }

    public void buttonManager(View v) {
        if(v == findViewById(R.id.button01)){
            setContentView(R.layout.activity_first_launch);
        } else if(v == findViewById(R.id.button02)) {
            setContentView(R.layout.activity_first_launch_done);
        } else if(v == findViewById(R.id.button03)) {
            setContentView(R.layout.activity_first_launch);
        } else if(v == findViewById(R.id.button04)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(v == findViewById(R.id.radioGroup)){
            Log.d("Radio", "Radio");
            radioButtonSelection();
        }
    }

    private void radioButtonSelection(){
        int selectedRadioButton = radioGroup.getCheckedRadioButtonId();

        switch (selectedRadioButton) {
            case R.id.radioYes:
                etLongTermIllness.setVisibility(View.VISIBLE);
                Log.d("Radio", "Radio yes");
                break;
            case R.id.radioNo:
                etLongTermIllness.setVisibility(View.INVISIBLE);
                break;
            }
    }
}