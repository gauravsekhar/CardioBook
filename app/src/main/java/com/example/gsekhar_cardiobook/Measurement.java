package com.example.gsekhar_cardiobook;

import java.util.UUID;

// This class contains field variables which are recorded for each measurement.
// It consists of a field variable 'uniqueID' which uniquely identifies the measurement records.
public class Measurement {

    private String dateMeasured;
    private String timeMeasured;
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;
    private String comment;
    private UUID uniqueID;      // unique measurement ID

    public Measurement(String dateMeasured, String timeMeasured, Integer systolicPressure, Integer diastolicPressure, Integer heartRate, String comment) {
        this.dateMeasured = dateMeasured;
        this.timeMeasured = timeMeasured;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
        this.uniqueID = UUID.randomUUID();  // generate random unique ID to identify record
    }

    public String getDateMeasured() { return dateMeasured; }

    public String getTimeMeasured() { return timeMeasured; }

    public Integer getSystolicPressure() { return systolicPressure; }

    public Integer getDiastolicPressure() { return diastolicPressure; }

    public Integer getHeartRate() { return heartRate; }

    public String getComment() { return comment; }

    public UUID getUniqueID() {return uniqueID; }

    public void setDateMeasured(String dateMeasured) { this.dateMeasured = dateMeasured; }

    public void setTimeMeasured(String timeMeasured) { this.timeMeasured = timeMeasured; }

    public void setSystolicPressure(Integer systolicPressure) { this.systolicPressure = systolicPressure; }

    public void setDiastolicPressure(Integer diastolicPressure) { this.diastolicPressure = diastolicPressure; }

    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }

    public void setComment(String comment) { this.comment = comment; }

}
