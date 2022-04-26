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

public class PainHistoryActivity extends AppCompatActivity {
    private PainHistoryBinding painHistoryBinding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.painHistoryBinding = PainHistoryBinding.inflate(getLayoutInflater());
        this.db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-db").allowMainThreadQueries().build();

        setContentView(painHistoryBinding.getRoot());

        List<PainLog> data = this.db.painLogDao().getAll();

        PainLogListRecyclerViewAdapter adapter = new PainLogListRecyclerViewAdapter(data, getApplication());

        this.painHistoryBinding.painHistoryRecyclerView.setAdapter(adapter);

        this.painHistoryBinding.painHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
