package com.ericaskari.healthapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class hello_fragmented extends AppCompatActivity {

    FragmentManager fragmentManager;
    Button btn1;
    Button btn2;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_fragmented);

        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.flFragment, FirstHelloFragment.class, null, "first")
                    .commit();
        }

        btn2 = findViewById(R.id.btnFragment2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentFragment = (Fragment) fragmentManager.findFragmentById(R.id.flFragment);
                String current = (String) currentFragment.getTag();

                if(current.equals("first")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("some_int", 0);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, SecondHelloFragment.class, bundle, "second")
                            .setReorderingAllowed(true)
                            .addToBackStack(null) // name can be null
                            .commit();
                } else if(current.equals("second")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("some_int", 0);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flFragment, FirstHelloFragment.class, bundle, "first")
                            .setReorderingAllowed(true)
                            .addToBackStack(null) // name can be null
                            .commit();
                }
            }
        });
    }
}