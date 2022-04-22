package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;

/**
 * @author Gavril Tschokkinen
 */

public class ReportPain extends AppCompatActivity {
    private Spinner selectPainSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_pain);

        selectPainSpinner = findViewById(R.id.selectPainSpinner);
        //https://developer.android.com/guide/topics/ui/controls/spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_pains, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPainSpinner.setAdapter(adapter);
    }
}