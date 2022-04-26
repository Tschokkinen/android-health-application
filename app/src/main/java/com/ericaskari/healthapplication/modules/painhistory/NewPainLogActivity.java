package com.ericaskari.healthapplication.modules.painhistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.ericaskari.healthapplication.MainActivity;
import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.databinding.NewPainLogBinding;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Gavril Tschokkinen
 */

public class NewPainLogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AppDatabase db;

    private com.ericaskari.healthapplication.databinding.NewPainLogBinding NewPainLogBinding;

    private Date currentTime; //Used to get time of pain log creation

    //View components
    private Spinner selectPainSpinner; //Used to display a list of pains
    private EditText editTextDescription; //Pain description field
    private TextView textViewPainStrength; //Pain strength view
    private EditText editTextMedicineTakenForThePain; //Taken medicine field
    private RadioGroup radioGroupLogPain;
    private EditText editTextTellAboutYourFeelings; //Feelings field

    //Strings
    private String selectedPain;
    private String description;
    private String descriptionDefault = "Ei kuvausta"; //Default description
    private String takenMedicine;
    private String takenMedicineDefault = "Ei otettua lääkettä."; //Default taken medicine
    private SeekBar seekBarHowStrongIsThePain;
    private String tellAboutYourFeelings;
    private String tellAboutYourFeelingsDefault = "Kipu ei vaikuttanut mielialaan.";

    //Ints
    private int howStrongIsThePain; //Pain strength


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());

        //Get database
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //Get Edit text and Text view views
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextMedicineTakenForThePain = findViewById(R.id.editTextMedicineTaken);
        textViewPainStrength = findViewById(R.id.textViewPainStrenght);
        editTextTellAboutYourFeelings = findViewById(R.id.editTextTellAboutYourFeelings);

        radioButtonSelection();

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
        if(v == findViewById(R.id.radioGroupLogPain)) {
            Log.i("Radio", "Radio button selection");
            radioButtonSelection();
        } else if (v == findViewById(R.id.buttonLogPainReady)) {
            Log.i("Button", "Button clicked");
            gatherData();
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
                    editTextMedicineTakenForThePain.setVisibility(View.VISIBLE);
                    break;
                case R.id.radioButtonPainLogNo:
                    editTextMedicineTakenForThePain.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * Gathers the user input from the activity and assigns default values as necessary.
     */
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

        //Check if user has reported taken any medication for the pain
        if(!editTextMedicineTakenForThePain.getText().toString().isEmpty()) {
            takenMedicine = editTextMedicineTakenForThePain.getText().toString();
        } else {
            takenMedicine = takenMedicineDefault; //Assign default value
        }

        //Check if user has reported any feelings regarding the pain
        if(!editTextTellAboutYourFeelings.getText().toString().isEmpty()) {
            tellAboutYourFeelings = editTextTellAboutYourFeelings.getText().toString();
        } else {
            tellAboutYourFeelings = tellAboutYourFeelingsDefault; //Assign default value
        }

        //Log.i("Pain", String.valueOf(howStrongIsThePain));

        saveData(); //Call saveData()
    }

    /**
     * Saves the data to the database
     */
    private void saveData() {
        PainLog painLog = new PainLog(currentTime, selectedPain, description, takenMedicine, howStrongIsThePain, tellAboutYourFeelings);
        this.db.painLogDao().insertAll(painLog);

        Log.i("PainLog", this.db.painLogDao().getAll().toString());

        Intent intent = new Intent(this, MainActivity.class);
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
