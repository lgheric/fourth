package com.eric.fourth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private CompassView cView;
    private SensorManager sManager;
    private Sensor mSensorOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cView = new CompassView(MainActivity.this);
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorOrientation = sManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sManager.registerListener(this, mSensorOrientation, SensorManager.SENSOR_DELAY_UI);
        setContentView(cView);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        cView.setDegree(event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sManager.unregisterListener(this);
    }
}