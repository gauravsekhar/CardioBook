package com.example.gsekhar_cardiobook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Measurement> measurements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StorageManager storageManager = new StorageManager(this);
        measurements = storageManager.getMeasurements();

        // call emptyMessage() to prompt user to click '+' button if no measurements are found
        emptyMessage();
        // initialize the measurements list
        initRecyclerView();
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
}
