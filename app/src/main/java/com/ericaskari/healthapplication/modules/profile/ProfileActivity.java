package com.ericaskari.healthapplication.modules.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.ProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ProfileBinding profileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(profileBinding.getRoot());
    }
}
