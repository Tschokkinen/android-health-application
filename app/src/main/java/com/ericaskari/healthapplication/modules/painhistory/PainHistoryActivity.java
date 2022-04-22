package com.ericaskari.healthapplication.modules.painhistory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.databinding.PainHistoryBinding;

public class PainHistoryActivity extends AppCompatActivity {
    private PainHistoryBinding painHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        painHistoryBinding = PainHistoryBinding.inflate(getLayoutInflater());
        setContentView(painHistoryBinding.getRoot());
    }
}
