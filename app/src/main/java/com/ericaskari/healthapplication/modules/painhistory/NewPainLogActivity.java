package com.ericaskari.healthapplication.modules.painhistory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.databinding.NewPainLogBinding;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Gavril Tschokkinen
 */

public class NewPainLogActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private NewPainLogBinding NewPainLogBinding;
    private Spinner selectPainSpinner; //Used to display a list of pains
    private Date currentTime; //Used to get time of pain log creation
    private String selectedPain;
    private String takenMedicine;
    private SeekBar seekBarHowStrongIsThePain;
    private int howStrongIsThePain;

    //Used to detect if user hasn't chosen any pain from the list.
    //Remember to update this String if String list for the dropdown menu is changed!!!
    private String noneOfTheAbove = "Ei mikään näistä";
    private EditText editTextDescribeOwnPain;
    private EditText editTextMedicineTakenForThePain;
    private RadioGroup radioGroupLogPain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());

        editTextDescribeOwnPain = findViewById(R.id.editTextDescribeOwnPain);
        editTextMedicineTakenForThePain = findViewById(R.id.editTextMedicineTaken);

        radioButtonSelection();

        seekBarHowStrongIsThePain = findViewById(R.id.seekBarHowStrongIsThePain);
        seekBarHowStrongIsThePain.setMax(10);
        seekBarHowStrongIsThePain.setProgress(1);

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
            saveData();
        }
    }

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

    private void saveData() {
        currentTime = Calendar.getInstance().getTime(); //Get time and date of log

        //If user has chosen as a pin "Ei mikään näistä" (none of the above),
        //get freely described pain before saving
        if(selectedPain.equals(noneOfTheAbove)) {
            selectedPain = editTextDescribeOwnPain.getText().toString();
        }

        //Check if user has logged any medicine. Otherwise set default text
        if(!editTextMedicineTakenForThePain.getText().toString().isEmpty()) {
            takenMedicine = editTextMedicineTakenForThePain.getText().toString();
        } else {
            takenMedicine = "Ei otettua lääkettä.";
        }

        howStrongIsThePain = seekBarHowStrongIsThePain.getProgress();

        //Log.i("Pain", String.valueOf(howStrongIsThePain));

        //Values to save to Room are listed here (description => variable name and type):
        //Time and date => currentTime (Date)
        //Type of pain => selectedPain (String)
        //Has medicine been taken => takenMedicine (String)
        //How strong is the pain => howStrongIsThePain (Int)
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedPain = adapterView.getItemAtPosition(i).toString();
        Log.i("onItemSelected", adapterView.getItemAtPosition(i).toString());

        if(selectedPain.equals(noneOfTheAbove)) {
            editTextDescribeOwnPain.setVisibility(View.VISIBLE);
        } else {
            editTextDescribeOwnPain.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
