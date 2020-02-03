package com.example.gsekhar_cardiobook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Measurement> measurements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageManager storageManager = new StorageManager(this, getString(R.string.file_name));
        measurements = storageManager.getMeasurements();

        // call emptyMessage() to prompt user to click '+' button if no measurements are found
        emptyMessage();
        // initialize the measurements list
        initRecyclerView();

        FloatingActionButton addMeasurementButton = (FloatingActionButton) findViewById(R.id.add_measurement_button);

        addMeasurementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditMeasurement(MainActivity.this, AddEditMeasurement.ADD, null);
            }
        });
    }

    // This function displays/hides the message displayed when ArrayList measurements is empty.
    private void emptyMessage() {
        TextView emptyMessage = findViewById(R.id.empty_message);

        if (measurements.size() == 0 || measurements == null) {
            emptyMessage.setVisibility(View.VISIBLE);
        } else {
            emptyMessage.setVisibility(View.INVISIBLE);
        }
    }

    // This function initializes the RecyclerView which lists the measurements in the database.
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, measurements);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static void addEditMeasurement(Context context, int mode, Measurement measurement) {
        Intent intent = new Intent(context, AddEditMeasurement.class);
        intent.putExtra(AddEditMeasurement.MODELABEL, mode);
        if (mode == AddEditMeasurement.EDIT) {
            Gson gson = new Gson();
            intent.putExtra(AddEditMeasurement.MEASUREMENTLABEL, gson.toJson(measurement));
        }
        context.startActivity(intent);
    }
}
