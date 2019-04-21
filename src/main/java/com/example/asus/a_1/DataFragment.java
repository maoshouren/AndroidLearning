package com.example.asus.a_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";

    public TextView text_temperature;
    public TextView text_humidity;
    public TextView text_pressure;
    public TextView text_light;
    public TextView text_ammonia;
    public TextView text_carbon;
    public TextView text_oxygen;
    public TextView text_pm;
    public TextView text_noise;
    public TextView text_longitude;
    public TextView text_latitude;
    public TextView text_time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        text_temperature = (TextView) view.findViewById(R.id.temperature);
        text_humidity = (TextView) view.findViewById(R.id.humidity);
        text_pressure = (TextView) view.findViewById(R.id.atmosphere_pressure);
        text_light = (TextView) view.findViewById(R.id.light_intensity);
        text_ammonia = (TextView) view.findViewById(R.id.ammonia_concentration);
        text_carbon = (TextView) view.findViewById(R.id.carbon_dioxide);
        text_oxygen = (TextView) view.findViewById(R.id.oxygen);
        text_pm = (TextView) view.findViewById(R.id.pm25);
        text_noise = (TextView) view.findViewById(R.id.noise);
        text_longitude = (TextView) view.findViewById(R.id.longitude);
        text_latitude = (TextView) view.findViewById(R.id.latitude);
        text_time = (TextView) view.findViewById(R.id.time);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

}
