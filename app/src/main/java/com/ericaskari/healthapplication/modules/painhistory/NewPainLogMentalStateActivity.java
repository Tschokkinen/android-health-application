package com.ericaskari.healthapplication.modules.painhistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.ericaskari.healthapplication.MainActivity;
import com.ericaskari.healthapplication.NotificationService;
import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Gathers together all of the input data related to a new pain and passess it to the database
 * @author Gavril Tschokkinen
 */

public class NewPainLogMentalStateActivity extends AppCompatActivity {
    AppDatabase db; //Database
    private EditText editTextTellAboutYourFeelings; //Feelings field

    private Date currentTime; //Used to get time of pain log creation

    //Strings
    private String takenMedicine;
    private String selectedPain;
    private String description;
    private String tellAboutYourFeelings;
    private String tellAboutYourFeelingsDefault = "Kipu ei vaikuttanut mielialaan.";

    //Ints
    private int howStrongIsThePain; //Pain strength

    /**
     * onCreate function
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pain_log_mental_state);

        //Get database
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //Get views
        editTextTellAboutYourFeelings = findViewById(R.id.editTextTellAboutYourFeelings);
    }

    /**
     * Button manager for the activity
     * @param v
     */
    public void buttonManager(View v) {
        if (v == findViewById(R.id.buttonLogPainMentalStateReady)) {
            //Log.i("Button", "Button clicked");
            gatherData(); //Start gathering user input
        }
    }

    /**
     * Gathers the user input from the activity and assigns default values as necessary.
     */
    private void gatherData() {
        //Check if user has reported any feelings regarding the pain
        if(!editTextTellAboutYourFeelings.getText().toString().isEmpty()) {
            tellAboutYourFeelings = editTextTellAboutYourFeelings.getText().toString();
        } else {
            tellAboutYourFeelings = tellAboutYourFeelingsDefault; //Assign default value if empty
        }

        //Get data from bundle sent by previous activity
        currentTime = (Date) getIntent().getSerializableExtra("Date");
        selectedPain = getIntent().getExtras().getString("Selected pain");
        description = getIntent().getExtras().getString("Description");
        howStrongIsThePain = getIntent().getExtras().getInt("Pain strength");
        takenMedicine = getIntent().getExtras().getString("Taken medicine");

        saveData(); //Call saveData()
    }

    /**
     * Saves the data to the database and creates a notification
     */
    private void saveData() {
        PainLog painLog = new PainLog(currentTime, selectedPain, description, takenMedicine, howStrongIsThePain, tellAboutYourFeelings);
        this.db.painLogDao().insertAll(painLog);

        //Log.i("PainLog", this.db.painLogDao().getAll().toString());

        //Start a notification service
        startService(new Intent(this, NotificationService.class));

        //Go back to main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); //Close current activity
    }
}