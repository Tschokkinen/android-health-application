package com.ericaskari.healthapplication.modules.painhistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Used to gather information related to new pain and to pass on data from previous activity
 * @author Gavril Tschokkinen
 */

public class NewPainLogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private com.ericaskari.healthapplication.databinding.NewPainLogBinding NewPainLogBinding;

    private Date currentTime; //Used to get time of pain log creation

    //View components
    private Spinner selectPainSpinner; //Used to display a list of pains
    private EditText editTextDescription; //Pain description field
    private TextView textViewPainStrength; //Pain strength view

    //Strings
    private String selectedPain;
    private String description;
    private String descriptionDefault = "Ei kuvausta"; //Default description
    private SeekBar seekBarHowStrongIsThePain;

    //Ints
    private int howStrongIsThePain; //Pain strength


    /**
     * onCreate is used to initialize activity and find views in activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());

        //Get Edit text and Text view views
        editTextDescription = findViewById(R.id.editTextDescription);
        textViewPainStrength = findViewById(R.id.textViewPainStrenght);

        //Get seekbar and assign max value and click interval when moved
        seekBarHowStrongIsThePain = findViewById(R.id.seekBarHowStrongIsThePain);
        seekBarHowStrongIsThePain.setMax(10);
        seekBarHowStrongIsThePain.setProgress(1);

        //Display current pain strength from seek bar to the user (1 by default on start)
        textViewPainStrength.setText(String.valueOf(seekBarHowStrongIsThePain.getProgress()));

        //Originally taken from https://abhiandroid.com/ui/seekbar, but modified to fit our use
        seekBarHowStrongIsThePain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 1;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                howStrongIsThePain = seekBarHowStrongIsThePain.getProgress();
                textViewPainStrength.setText(String.valueOf(howStrongIsThePain));
            }
        });

        //Drop down list related (Spinner)
        selectPainSpinner = findViewById(R.id.selectPainSpinner); //Find spinner
        selectPainSpinner.setOnItemSelectedListener(this); //Set spinner listener
        //https://developer.android.com/guide/topics/ui/controls/spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_pains, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPainSpinner.setAdapter(adapter);
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v) {
        if (v == findViewById(R.id.buttonLogPainReady)) {
            Log.i("Button", "Button clicked");
            gatherData(); //Start gathering user input
        }
    }

    /**
     * Gathers the user input from the activity and assigns default values as necessary.
     * Finally passes the data on to the next activity
     */
    //Gathers all of the data and passes it on to the next activity
    private void gatherData() {
        currentTime = Calendar.getInstance().getTime(); //Get time and date of log

        //Has user given any description regarding the reported pain
        if(!editTextDescription.getText().toString().isEmpty()) {
            description = editTextDescription.getText().toString(); //Get description
        } else {
            description = descriptionDefault; //Assign default value
        }

        //Get pain strength (int)
        howStrongIsThePain = seekBarHowStrongIsThePain.getProgress();

        //Bundle data
        Bundle bundle = new Bundle();
        bundle.putSerializable("Date", currentTime);
        bundle.putString("Selected pain", selectedPain);
        bundle.putString("Description", description);
        bundle.putInt("Pain strength", howStrongIsThePain);

        //Go to the next activity and pass on the gathered data
        Intent intent = new Intent(this, NewPainLogMedicineActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedPain = adapterView.getItemAtPosition(i).toString();
        Log.i("onItemSelected", adapterView.getItemAtPosition(i).toString());
    }

    /**
     *
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
