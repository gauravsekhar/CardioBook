package com.example.gsekhar_cardiobook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

// This class is used to store all of the data needed for the app.
// All data is stored in a text file as a json string using the Gson class.
public class StorageManager {
    private Context context;
    private File file;
    private Gson gson;

    // class constructor
    public StorageManager(Context context, String fileName) {
        this.context = context;
        this.file = new File(context.getFilesDir(), fileName);
        this.gson = new Gson();

    }

    // called from AddEditMeasurement if user adds valid measurement record
    public void addMeasurement(Measurement measurement){
        ArrayList<Measurement> measurements = getMeasurements();
        measurements.add(measurement);
        writeToFile(measurements);
    }

    // called from AddEditMeasurement if user updates measurement record
    public void updateMeasurement(Measurement measurement){
        ArrayList<Measurement> measurements = getMeasurements();
        for (int i=0; i<measurements.size(); i++){
            Measurement m = measurements.get(i);
            if (m.getUniqueID().equals(measurement.getUniqueID())){
                measurements.set(i, measurement);
            }
        }
        writeToFile(measurements);
    }

    // called from AddEditMeasurement if user requests deletion by pressing delete button
    public void deleteMeasurement(Measurement measurement){
        ArrayList<Measurement> measurements = getMeasurements();

        int removalID = -1;
        for (int i=0; i<measurements.size(); i++){
            Measurement m = measurements.get(i);
            if (m.getUniqueID().equals(measurement.getUniqueID())){
                removalID = i;
                break;
            }
        }

        if (removalID < 0){
            Log.d(context.getString(R.string.testGeneralLog), "Unable to delete");
        }

        measurements.remove(removalID);
        writeToFile(measurements);
    }


    private void writeToFile(ArrayList<Measurement> measurements){
        String data = gson.toJson(measurements);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }
        catch (IOException e) {
            Log.e(context.getString(R.string.testGeneralLog), e.toString());
        }
    }

    public ArrayList<Measurement> getMeasurements(){
        int fileLength = (int) file.length();

        byte[] byteArray = new byte[fileLength];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteArray);
            fileInputStream.close();
        }
        catch (FileNotFoundException e) {
            Log.e(context.getString(R.string.testGeneralLog), e.toString());
        }
        catch (IOException e) {
            Log.e(context.getString(R.string.testGeneralLog), e.toString());
        }

        String data = new String(byteArray);
        Type arrayListMeasurementType = new TypeToken<ArrayList<Measurement>>(){}.getType();

        ArrayList<Measurement> measurements = gson.fromJson(data, arrayListMeasurementType);

        if (measurements == null) {
            return new ArrayList<Measurement>();
        } else { return measurements; }
    }

}
