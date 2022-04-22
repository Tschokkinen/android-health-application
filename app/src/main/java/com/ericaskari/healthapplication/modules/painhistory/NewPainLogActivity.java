package com.ericaskari.healthapplication.modules.painhistory;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.databinding.NewPainLogBinding;

/**
 * @author Gavril Tschokkinen
 */

public class NewPainLogActivity extends AppCompatActivity {
    private NewPainLogBinding NewPainLogBinding;
    private Spinner selectPainSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());

        selectPainSpinner = findViewById(R.id.selectPainSpinner);
        //https://developer.android.com/guide/topics/ui/controls/spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list_of_pains, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectPainSpinner.setAdapter(adapter);
    }
}
