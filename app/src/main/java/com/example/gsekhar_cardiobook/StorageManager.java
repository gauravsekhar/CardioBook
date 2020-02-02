package com.example.gsekhar_cardiobook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Measure;

import java.util.ArrayList;

import static com.example.gsekhar_cardiobook.FeedReaderContract.FeedEntry.*;

public class StorageManager extends SQLiteOpenHelper {

    private static final String TAG = "Storage Manager";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public StorageManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + FeedReaderContract.FeedEntry._ID
                + " INTEGER PRIMARY KEY, " + COLUMN_NAME_DATE + " DATE, " + COLUMN_NAME_TIME
                + " TIME, " + COLUMN_NAME_SYS_PRESSURE + " INTEGER, " + COLUMN_NAME_DIA_PRESSURE
                + " INTEGER, " + COLUMN_NAME_HEART_RATE + " INTEGER, " + COLUMN_NAME_COMMENT
                + " VARCHAR(20))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean addMeasurement(Measurement measurement) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_DATE, measurement.getDateMeasured());
        values.put(COLUMN_NAME_TIME, measurement.getTimeMeasured());
        values.put(COLUMN_NAME_SYS_PRESSURE, measurement.getSystolicPressure());
        values.put(COLUMN_NAME_DIA_PRESSURE, measurement.getDiastolicPressure());
        values.put(COLUMN_NAME_HEART_RATE, measurement.getHeartRate());
        values.put(COLUMN_NAME_COMMENT, measurement.getComment());

        long result = db.insert(TABLE_NAME, null, values);

        return (result == -1) ? Boolean.FALSE : Boolean.TRUE;
    }

    public ArrayList<Measurement> getMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<Measurement>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // if the table is not empty, i.e. if a record exists in the first row
        if (cursor.moveToFirst()) {
            // get individual measurements and insert into list
            do {
                Measurement measurement = new Measurement();
                measurement.setDateMeasured(cursor.getString(0));
                measurement.setTimeMeasured(cursor.getString(1));
                measurement.setSystolicPressure(Integer.parseInt(cursor.getString(2)));
                measurement.setDiastolicPressure(Integer.parseInt(cursor.getString(3)));
                measurement.setHeartRate(Integer.parseInt(cursor.getString(4)));
                measurement.setComment(cursor.getString(5));

                measurements.add(measurement);
            } while (cursor.moveToNext());
        }

        return measurements;
    }

}
