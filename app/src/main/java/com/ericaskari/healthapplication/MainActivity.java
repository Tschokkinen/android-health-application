package com.ericaskari.healthapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.ericaskari.healthapplication.daos.UserDao;
import com.ericaskari.healthapplication.models.User;
import com.ericaskari.healthapplication.services.AppDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.ericaskari.healthapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding activityMainBinding;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").build();

        AsyncTask.execute(() -> {
            //  Get all users from Database
            List<User> users = this.db.userDao().getAll();

            //  Check if first init activity is needed.
            if (users.toArray().length == 0) {
                Intent intent = new Intent(this, FirstLaunch.class);
                startActivity(intent);
            } else {
                setContentView(activityMainBinding.getRoot());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}