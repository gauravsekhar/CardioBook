package com.example.gsekhar_cardiobook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

// This class handles the adding, editing, and removing measurements.
public class AddEditMeasurement extends AppCompatActivity {

    private StorageManager storageManager;

    // Constant strings/values used for selecting the mode and intent keys
    public static final String MODELABEL = "com.example.gsekhar.gsekhar_cardiobook.MODE";
    public static final String MEASUREMENTLABEL = "com.example.gsekhar.gsekhar_cardiobook.MEASUREMENT";
    public static final int ADD = 0;
    public static final int EDIT = 1;

    private Measurement editingMeasurement; // measurement to be edited in EDIT mode

    private int mode;   // the mode, i.e. ADD or EDIT


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_measurement);

        checkMode();
        storageManager = new StorageManager(this, getString(R.string.file_name));
    }

    // determine mode and call appropriate method
    private void checkMode() {
        Intent intent = getIntent();
        mode = intent.getIntExtra(AddEditMeasurement.MODELABEL, -1);

        if (mode == AddEditMeasurement.ADD) {
            initAddMode();
        }
        else if (mode == AddEditMeasurement.EDIT) {
            String data = intent.getStringExtra(AddEditMeasurement.MEASUREMENTLABEL);
            initEditMode(data);
        }
    }

    private void returnToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    // this function is called if confirm button is pressed
    private void confirmButtonClick() {
        String dateInput = ((EditText) findViewById(R.id.form_date)).getText().toString();
        String timeInput = ((EditText) findViewById(R.id.form_time)).getText().toString();
        String systolicInput = ((EditText) findViewById(R.id.form_systolic)).getText().toString();
        String diastolicInput = ((EditText) findViewById(R.id.form_diastolic)).getText().toString();
        String heartRateInput = ((EditText) findViewById(R.id.form_heart_rate)).getText().toString();
        String commentInput = ((EditText) findViewById(R.id.form_comment)).getText().toString();

        // pass inputs to validator
        Validator validator = new Validator(this, dateInput, timeInput, systolicInput,
                diastolicInput, heartRateInput, commentInput, mode);

        // check if inputs are valid
        Boolean valid = validator.validate();

        // display message received from validator
        Toast.makeText(this, validator.getMessage(), Toast.LENGTH_SHORT).show();

        // if valid, apply changes
        if (valid) {
            switch(mode) {
                case AddEditMeasurement.ADD:
                    Measurement measurement = new Measurement(dateInput, timeInput, Integer.valueOf(systolicInput), Integer.valueOf(diastolicInput), Integer.valueOf(heartRateInput), commentInput);
                    storageManager.addMeasurement(measurement);
                    break;
                case AddEditMeasurement.EDIT:
                    editingMeasurement.setDateMeasured(dateInput);
                    editingMeasurement.setTimeMeasured(timeInput);
                    editingMeasurement.setSystolicPressure(Integer.valueOf(systolicInput));
                    editingMeasurement.setDiastolicPressure(Integer.valueOf(diastolicInput));
                    editingMeasurement.setHeartRate(Integer.valueOf(heartRateInput));
                    editingMeasurement.setComment(commentInput);

                    storageManager.updateMeasurement(editingMeasurement);
                    break;
            }
        }
        returnToMainActivity();
    }

    // this function is called if delete button is pressed
    private void deleteButtonClick() {
        switch(mode) {
            case AddEditMeasurement.ADD:
                break;
            case AddEditMeasurement.EDIT:
                storageManager.deleteMeasurement(editingMeasurement);
                break;
        }
        returnToMainActivity();
    }

    private void initAddMode() {
        // action to be performed when confirm button is pressed
        FloatingActionButton submit = findViewById(R.id.fab_confirm);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmButtonClick();
            }
        });

        // return to main activity if delete button is pressed
        FloatingActionButton delete = findViewById(R.id.fab_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClick();
            }
        });

    }
    
    private void initEditMode(String data) {

        Gson gson = new Gson();
        editingMeasurement = gson.fromJson(data, Measurement.class);

        // link elements and autofill previous values
        EditText date = findViewById(R.id.form_date);
        date.setText(editingMeasurement.getDateMeasured());
        EditText time = findViewById(R.id.form_time);
        time.setText(editingMeasurement.getTimeMeasured());
        EditText systolic = findViewById(R.id.form_systolic);
        systolic.setText(Integer.toString(editingMeasurement.getSystolicPressure()));
        EditText diastolic = findViewById(R.id.form_diastolic);
        diastolic.setText(Integer.toString(editingMeasurement.getDiastolicPressure()));
        EditText heartRate = findViewById(R.id.form_heart_rate);
        heartRate.setText(Integer.toString(editingMeasurement.getHeartRate()));
        EditText comment = findViewById(R.id.form_comment);
        comment.setText(editingMeasurement.getComment());

        FloatingActionButton submit = findViewById(R.id.fab_confirm);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmButtonClick();
            }
        });

        FloatingActionButton delete = findViewById(R.id.fab_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClick();
            }
        });

    }

}
