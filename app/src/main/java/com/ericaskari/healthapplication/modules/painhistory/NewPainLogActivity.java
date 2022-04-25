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
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Gavril Tschokkinen
 */

public class NewPainLogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AppDatabase db;

    private NewPainLogBinding NewPainLogBinding;
    private Spinner selectPainSpinner; //Used to display a list of pains
    private Date currentTime; //Used to get time of pain log creation
    private String selectedPain;
    private String description;
    private String descriptionDefault = "Ei kuvausta";
    private String takenMedicine;
    private String takenMedicineDefault = "Ei otettua lääkettä.";
    private SeekBar seekBarHowStrongIsThePain;
    private int howStrongIsThePain;

    private TextView textViewPainStrength;

    private EditText editTextTellAboutYourFeelings;
    private String tellAboutYourFeelings;
    private String tellAboutYourFeelingsDefault = "Kipu ei vaikuttanut mielialaan.";

    //Used to detect if user hasn't chosen any pain from the list.
    //Remember to update this String if String list for the dropdown menu is changed!!!
    private String noneOfTheAbove = "Ei mikään näistä";
    private EditText editTextDescription;
    private EditText editTextMedicineTakenForThePain;
    private RadioGroup radioGroupLogPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());

        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextMedicineTakenForThePain = findViewById(R.id.editTextMedicineTaken);
        textViewPainStrength = findViewById(R.id.textViewPainStrenght);
        editTextTellAboutYourFeelings = findViewById(R.id.editTextTellAboutYourFeelings);

        radioButtonSelection();

        seekBarHowStrongIsThePain = findViewById(R.id.seekBarHowStrongIsThePain);
        seekBarHowStrongIsThePain.setMax(10);
        seekBarHowStrongIsThePain.setProgress(1);

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

        //Drop down list (spinner)
        selectPainSpinner = findViewById(R.id.selectPainSpinner);
        selectPainSpinner.setOnItemSelectedListener(this);
        //https://developer.android.com/guide/topics/ui/controls/spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_pains, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPainSpinner.setAdapter(adapter);
    }

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
     *
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

    private void gatherData() {
        currentTime = Calendar.getInstance().getTime(); //Get time and date of log

        description = editTextDescription.getText().toString();

        if(description.isEmpty()) {
            description = descriptionDefault;
        }
        howStrongIsThePain = seekBarHowStrongIsThePain.getProgress();

        //Check if user has logged any medicine. Otherwise set default text
        if(!editTextMedicineTakenForThePain.getText().toString().isEmpty()) {
            takenMedicine = editTextMedicineTakenForThePain.getText().toString();
        } else {
            takenMedicine = takenMedicineDefault;
        }

        tellAboutYourFeelings = editTextTellAboutYourFeelings.getText().toString();

        if(tellAboutYourFeelings.isEmpty()) {
            tellAboutYourFeelings = tellAboutYourFeelingsDefault;
        }

        //Log.i("Pain", String.valueOf(howStrongIsThePain));

        //Send gathered data for saving
        saveData(currentTime, selectedPain, description, takenMedicine, howStrongIsThePain, tellAboutYourFeelings);
    }

    /**
     *
     * @param currentTime
     * @param selectedPain
     * @param description
     * @param takenMedicine
     * @param howStrongIsThePain
     * @param tellAboutYourFeelings
     */
    private void saveData(Date currentTime, String selectedPain, String description, String takenMedicine, int howStrongIsThePain, String tellAboutYourFeelings) {
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

        /*
        if(selectedPain.equals(noneOfTheAbove)) {
            editTextDescribeOwnPain.setVisibility(View.VISIBLE);
        } else {
            editTextDescribeOwnPain.setVisibility(View.INVISIBLE);
        }
        */
    }

    /**
     *
     * @param adapterView
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
