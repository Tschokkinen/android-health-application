package com.ericaskari.healthapplication.modules.painhistory;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ericaskari.healthapplication.R;
import com.ericaskari.healthapplication.adapters.PainLogListRecyclerViewAdapter;
import com.ericaskari.healthapplication.databinding.PainHistoryBinding;
import com.ericaskari.healthapplication.models.PainLog;
import com.ericaskari.healthapplication.services.AppDatabase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.db = AppDatabase.getInstance(getApplicationContext());

        //  Show User the view
        setContentView(painHistoryBinding.getRoot());

        //  Get logs
        List<PainLog> painLogList = this.db.painLogDao().getAll();

        initializeRecycleView(painLogList);

        initializePieChart(painLogList);
    }

    private void initializeRecycleView(List<PainLog> painLogList) {
        //  Create RecyclerViewAdapter
        PainLogListRecyclerViewAdapter adapter = new PainLogListRecyclerViewAdapter(painLogList, getApplication());

        //  Set recyclerView adapter
        this.painHistoryBinding.painHistoryRecyclerView.setAdapter(adapter);

        //  Set List layout.
        this.painHistoryBinding.painHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * source: https://github.com/PhilJay/MPAndroidChart
     */
    private void initializePieChart(List<PainLog> painLogList) {

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        float[] yData = {25.3f, 10.3f, 66.3f, 44.3f, 16.3f, 46.3f, 23.3f};

        String[] listOfPains = getResources().getStringArray(R.array.list_of_pains);

        for (String painName : listOfPains) {
            List<PainLog> painLogs = painLogList.stream().filter(painLog -> painLog.bodyPart.equals(painName)).collect(Collectors.toList());

            //  See how many entries we have
            int count = painLogs.size();

            pieEntries.add(new PieEntry(count, painName));
        }


        PieDataSet dataSet = new PieDataSet(pieEntries, "");

        PieData data = new PieData(dataSet);

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }

        dataSet.setColors(colors);

        data.setValueTextColor(Color.BLACK);

        this.painHistoryBinding.pieChart.setData(data);
        this.painHistoryBinding.pieChart.setDrawEntryLabels(false);
        //  undo all highlights

        Description description = new Description();
        description.setText("");

        this.painHistoryBinding.pieChart.setDescription(description);

        this.painHistoryBinding.pieChart.invalidate();
    }

}
