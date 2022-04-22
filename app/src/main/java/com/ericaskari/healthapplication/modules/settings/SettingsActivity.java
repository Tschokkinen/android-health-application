package com.ericaskari.healthapplication.modules.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.SettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private SettingsBinding settingsActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsActivityBinding = SettingsBinding.inflate(getLayoutInflater());
        setContentView(settingsActivityBinding.getRoot());
    }
}
