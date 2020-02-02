package com.example.gsekhar_cardiobook;

import android.icu.util.Measure;

import java.util.UUID;

// This class contains field variables which are recorded for each measurement.
public class Measurement {

    private String dateMeasured;
    private String timeMeasured;
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;
    private String comment;

    public Measurement() { }

    public Measurement(String dateMeasured, String timeMeasured, Integer systolicPressure, Integer diastolicPressure, Integer heartRate, String comment) {
        this.dateMeasured = dateMeasured;
        this.timeMeasured = timeMeasured;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public String getDateMeasured() { return dateMeasured; }

    public String getTimeMeasured() { return timeMeasured; }

    public Integer getSystolicPressure() { return systolicPressure; }

    public Integer getDiastolicPressure() { return diastolicPressure; }

    public Integer getHeartRate() { return heartRate; }

    public String getComment() { return comment; }

    public void setDateMeasured(String dateMeasured) { this.dateMeasured = dateMeasured; }

    public void setTimeMeasured(String timeMeasured) { this.timeMeasured = timeMeasured; }

    public void setSystolicPressure(Integer systolicPressure) { this.systolicPressure = systolicPressure; }

    public void setDiastolicPressure(Integer diastolicPressure) { this.diastolicPressure = diastolicPressure; }

    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }

    public void setComment(String comment) { this.comment = comment; }

}
