package com.ericaskari.healthapplication;

import static android.content.ContentValues.TAG;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").build();

        AsyncTask.execute(() -> {
            UserDao userDao = db.userDao();
            List<User> users = userDao.getAll();

            Log.d(TAG, "users.toArray().length: " + users.toArray().length);

            //  Just to debug
            for (int i = 0; i < users.toArray().length; i++) {
                Log.d(TAG, "onCreate: " + users.toArray()[i]);
            }

            //  We can check if users.toArray().length is zero then it means that app is not initialized.
            if (users.toArray().length == 0) {
                //  TODO: Load the another activity and first launch stuff
                User user = new User("John", "Doe", new Date(), 55);
                userDao.insertAll(user);
                setContentView(activityMainBinding.getRoot());
            } else {
                setContentView(activityMainBinding.getRoot());
            }

            setSupportActionBar(activityMainBinding.toolbar);
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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