package com.ericaskari.healthapplication.modules.medicines;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.MedicinesBinding;

public class MedicinesActivity extends AppCompatActivity {
    private MedicinesBinding medicinesActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicinesActivityBinding = MedicinesBinding.inflate(getLayoutInflater());
        setContentView(medicinesActivityBinding.getRoot());

    }
}
