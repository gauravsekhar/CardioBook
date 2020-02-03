package com.example.gsekhar_cardiobook;

import android.content.Context;

import java.util.regex.Pattern;

/*  This class determines if the data input is valid. Each parameter is validated in a separate
    function and a cumulative function determines if all inputs are valid or not. A message is
    also returned to output if the measurement was stored successfully. */
public class Validator {

    private Context context;
    private String date;
    private String time;
    private String systolicPressure;
    private String diastolicPressure;
    private String heartRate;
    private String comment;
    private String message;
    private int mode;

    /*  String types of all parameters are passed into the Validator in order to check validity
        through use of regular expressions */
    public Validator(Context context, String date, String time, String systolicPressure,
                      String diastolicPressure, String heartRate, String comment, int mode) {

        this.context = context;
        this.date = date;
        this.time = time;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
        this.message = null;
        this.mode = mode;
    }

    public String getMessage() { return message; }

    private Boolean validateDate() {
        // date must be in the form YYYY-MM-DD
        Boolean valid = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(date).matches();

        if (message == null && !valid) {
            message = "Please enter valid date";
        }

        return valid;
    }

    private Boolean validateTime() {
        // time must be in the form HH:MM
        Boolean valid = Pattern.compile("[0-9]{2}:[0-9]{2}").matcher(time).matches();

        if (message == null && !valid) {
            message = "Please enter valid time";
        }

        return valid;
    }

    private Boolean validateSystolicPressure() {
        // systolic pressure must be non-negative integer
        Boolean valid = Pattern.compile("[0-9]+").matcher(systolicPressure).matches();

        if (message == null && !valid) {
            message = "Please enter valid systolic pressure";
        }

        return valid;
    }

    private Boolean validateDiastolicPressure() {
        // diastolic pressure must be a non-negative integer
        Boolean valid = Pattern.compile("[0-9]+").matcher(diastolicPressure).matches();

        if (message == null && !valid) {
            message = "Please enter valid diastolic pressure";
        }

        return valid;
    }

    private Boolean validateHeartRate() {
        // heart rate must be a non-negative integer
        Boolean valid = Pattern.compile("[0-9]+").matcher(heartRate).matches();

        if (message == null && !valid) {
            message = "Please enter valid heart rate";
        }

        return valid;
    }

    private Boolean validateComment() {
        Boolean valid = (comment.length() <= 20);   // comment is valid up to 20 character

        if (message == null && !valid) {
            message = "Max. commment length: 20";
        }

        return valid;
    }

    public Boolean validate() {
        Boolean valid = (validateDate() && validateTime() && validateSystolicPressure() &&
                validateDiastolicPressure() && validateHeartRate() && validateComment());

        if (valid) {
            if (mode == AddEditMeasurement.ADD) {
                message = "Measurement Successfully Added!";
            } else {
                message = "Measurement Successfully Updated!";
            }
        }

        return valid;
    }
}
