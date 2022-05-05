package com.ericaskari.healthapplication.modules.painhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.ericaskari.healthapplication.R;

import java.util.Date;

/**
 * Used to gather the medicine taken for the pain and to pass on data from previous activity
 * @author Gavril Tschokkinen
 */

public class NewPainLogMedicineActivity extends AppCompatActivity {
    //View elements
    private EditText editTextMedicineTakenForThePain; //Taken medicine field
    private RadioGroup radioGroupLogPain;

    private Date currentTime; //Used to get time of pain log creation

    //Strings
    private String takenMedicine;
    private String takenMedicineDefault = "Ei otettua lääkettä."; //Default taken medicine

    /**
     * onCreate function
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pain_log_medicine);

        editTextMedicineTakenForThePain = findViewById(R.id.editTextMedicineTaken);

        radioButtonSelection();
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v) {
        if(v == findViewById(R.id.radioGroupLogPain)) {
            //Log.i("Radio", "Radio button selection");
            radioButtonSelection();
        } else if (v == findViewById(R.id.buttonLogPainMedicineReady)) {
            //Log.i("Button", "Button clicked");
            gatherData(); //Start gathering user input
        }
    }

    /**
     * Manages radio button selection
     */
    private void radioButtonSelection() {
        radioGroupLogPain = findViewById(R.id.radioGroupLogPain);
        radioGroupLogPain.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radioButtonPainLogYes:
                    //Set text view active
                    editTextMedicineTakenForThePain.setVisibility(View.VISIBLE);
                    break;
                case R.id.radioButtonPainLogNo:
                    //Set text view inactive
                    editTextMedicineTakenForThePain.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * Gathers the user input from the activity and assigns default values as necessary.
     * Finally passes the data on to the next activity
     */
    //Gathers all of the data and passes it on to the next activity
    private void gatherData() {
        //Check if user has reported taken any medication for the pain
        if(!editTextMedicineTakenForThePain.getText().toString().isEmpty()) {
            takenMedicine = editTextMedicineTakenForThePain.getText().toString();
        } else {
            takenMedicine = takenMedicineDefault; //Assign default value
        }

        //Bundle and re-bundle data from previous activity
        Bundle bundle = new Bundle();
        bundle.putSerializable("Date", (Date) getIntent().getSerializableExtra("Date"));
        bundle.putString("Selected pain", getIntent().getExtras().getString("Selected pain"));
        bundle.putString("Description", getIntent().getExtras().getString("Description"));
        bundle.putInt("Pain strength", getIntent().getExtras().getInt("Pain strength"));
        bundle.putString("Taken medicine", takenMedicine);

        //Go to the next activity and pass on the gathered data
        Intent intent = new Intent(this, NewPainLogMentalStateActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}