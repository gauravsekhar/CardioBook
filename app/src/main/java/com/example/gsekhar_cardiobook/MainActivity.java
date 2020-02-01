package com.example.gsekhar_cardiobook;

import androidx.appcompat.app.AppCompatActivity;

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
        addButton();
        emptyText();
    }

    // This function displays/hides the message displayed when ArrayList measurements is empty
    private void emptyText() {
        TextView emptyText = findViewById(R.id.empty_text);

        if (measurements.size() == 0 || measurements == null) {
            emptyText.setVisibility(View.VISIBLE);
        } else {
            emptyText.setVisibility(View.INVISIBLE);
        }
    }

    private void addButton() {
        FloatingActionButton addMeasurementButton = findViewById(R.id.add_measurement_button);
        addMeasurementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert true;
            }
        });
    }
}
