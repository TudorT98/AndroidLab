package com.example.lab2;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    ListView sensorList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sensorList = findViewById(R.id.SensorsList);
        SensorManager sensorManager = (SensorManager) this.getSystemService(this.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<String> sensorName = new ArrayList<>();

        for(int i = 0 ; i < sensors.size() ; i++)
        {
            sensorName.add(sensors.get(i).getName() + sensors.get(i));
        }
        ArrayAdapter<String> sensorAdapter = new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,sensorName);
        sensorList.setAdapter(sensorAdapter);

    }

}
