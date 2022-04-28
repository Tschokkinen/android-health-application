package com.ericaskari.healthapplication.modules.painhistory;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.ericaskari.healthapplication.adapters.PainLogListRecyclerViewAdapter;
import com.ericaskari.healthapplication.databinding.PainHistoryBinding;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.services.AppDatabase;

import java.util.List;

/**
 *
 * @author Mohammad Askari (Eric)
 */
public class PainHistoryActivity extends AppCompatActivity {
    private PainHistoryBinding painHistoryBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Save view reference
        this.painHistoryBinding = PainHistoryBinding.inflate(getLayoutInflater());

        //  Save Database reference
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        //  Show User the view
        setContentView(painHistoryBinding.getRoot());

        //  Get logs
        List<PainLog> data = this.db.painLogDao().getAll();

        //  Get DB users
        PainLogListRecyclerViewAdapter adapter = new PainLogListRecyclerViewAdapter(data, getApplication());

        //  Set recyclerView adapter
        this.painHistoryBinding.painHistoryRecyclerView.setAdapter(adapter);

        //  Set List layout.
        this.painHistoryBinding.painHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
