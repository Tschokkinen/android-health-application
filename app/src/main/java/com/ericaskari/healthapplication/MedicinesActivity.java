package com.ericaskari.healthapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MedicineActivity
 */
public class MedicinesActivity extends AppCompatActivity {
    private MedicinesBinding medicinesActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicinesActivityBinding = MedicinesBinding.inflate(getLayoutInflater());
        setContentView(medicinesActivityBinding.getRoot());

    }
}
