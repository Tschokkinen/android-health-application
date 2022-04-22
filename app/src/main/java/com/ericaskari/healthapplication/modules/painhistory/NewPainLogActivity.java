package com.ericaskari.healthapplication.modules.painhistory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.NewPainLogBinding;

public class NewPainLogActivity extends AppCompatActivity {
    private NewPainLogBinding NewPainLogBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewPainLogBinding = NewPainLogBinding.inflate(getLayoutInflater());
        setContentView(NewPainLogBinding.getRoot());
    }
}
