package com.example.gsekhar_cardiobook;

import android.provider.BaseColumns;

// This class initializes some constants for creating and accessing the SQLite database.
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    // Inner class that defines the table contents
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "measurements";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String COLUMN_NAME_TIME = "Time";
        public static final String COLUMN_NAME_SYS_PRESSURE = "Systolic Pressure";
        public static final String COLUMN_NAME_DIA_PRESSURE = "Diastolic Pressure";
        public static final String COLUMN_NAME_HEART_RATE = "Heart Rate";
        public static final String COLUMN_NAME_COMMENT = "Comment";
    }
}