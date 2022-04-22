package com.ericaskari.healthapplication.modules.profile;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.databinding.ProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ProfileBinding profileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileBinding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(profileBinding.getRoot());
    }

    /**
     * Assign custom Navbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_main, menu);
        return true;
    }

    /**
     * Listen to clicks on Custom Navbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile_menu_edit) {
            onProfileMenuEditButtonClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onProfileMenuEditButtonClick() {
        Log.d(TAG, "onProfileMenuEditButtonClick: ");
    }
}
