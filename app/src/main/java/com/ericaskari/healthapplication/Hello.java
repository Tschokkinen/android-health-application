package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Hello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
    }

    public void buttonManager(View v){
        if(v == findViewById(R.id.nextButtonHello)){
            Intent intent = new Intent(this, FirstLaunch.class);
            startActivity(intent);
        }
    }
}