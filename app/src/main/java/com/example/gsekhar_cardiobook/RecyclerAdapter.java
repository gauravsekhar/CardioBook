package com.example.gsekhar_cardiobook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


// This class is responsible for implementing the list of measurements in the main activity.
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Measurement> measurements;

    // Constructor
    public RecyclerAdapter (Context context, ArrayList<Measurement> measurements){
        this.context = context;
        this.measurements = measurements;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView date;
        TextView systolicPressure;
        TextView diastolicPressure;
        TextView heartRate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.measurement_list_row);
            date = itemView.findViewById(R.id.list_date);
            systolicPressure = itemView.findViewById(R.id.list_systolic);
            diastolicPressure = itemView.findViewById(R.id.list_diastolic);
            heartRate = itemView.findViewById(R.id.list_heart_rate);
        }
    }

    // links the recycler view to measurement_list.xml
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.measurement_list, parent, false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    // binds data element of each measurement to text element and highlights unusual pressures
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Integer systolicPressureValue = measurements.get(position).getSystolicPressure();
        Integer diastolicPressureValue = measurements.get(position).getDiastolicPressure();

        String dateText = "Date Measured: " + measurements.get(position).getDateMeasured();
        String systolicPressureText = "Systolic Pressure : " + systolicPressureValue;
        String diastolicPressureText = "Diastolic Pressure : " + diastolicPressureValue;
        String heartRateText = "Heart Rate : " + measurements.get(position).getHeartRate();

        // update the text of elements to contain values
        holder.date.setText(dateText);
        holder.systolicPressure.setText(systolicPressureText);
        holder.diastolicPressure.setText(diastolicPressureText);
        holder.heartRate.setText(heartRateText);

        Boolean abnormalSystolicPressure = (systolicPressureValue < 90 || systolicPressureValue > 140);
        Boolean abnormalDiastolicPressure = (diastolicPressureValue < 60 || diastolicPressureValue > 90);

        // highlight the systolic pressure if abnormal
        if (abnormalSystolicPressure) {
            holder.systolicPressure.setTextColor(Color.RED);
        } else holder.systolicPressure.setTextColor(Color.BLACK);

        // highlight the diastolic pressure if abnormal
        if (abnormalDiastolicPressure) {
            holder.diastolicPressure.setTextColor(Color.RED);
        } else holder.diastolicPressure.setTextColor(Color.BLACK);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.addEditMeasurement(context, AddEditMeasurement.EDIT, measurements.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return measurements.size();
    }


}
