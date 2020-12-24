package com.eric.fourth;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txt_show;
    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        txt_show = (TextView) findViewById(R.id.txt_show);

        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sb = new StringBuilder();

        sb.append("此手机有").append(allSensors.size()).append("个传感器，分别有：\n\n");
        for(Sensor s:allSensors){
            switch (s.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    sb.append(s.getType()).append(" 加速度传感器(Accelerometer sensor)").append("\n");
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    sb.append(s.getType()).append(" 陀螺仪传感器(Gyroscope sensor)").append("\n");
                    break;
                case Sensor.TYPE_LIGHT:
                    sb.append(s.getType()).append(" 光线传感器(Light sensor)").append("\n");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    sb.append(s.getType()).append(" 磁场传感器(Magnetic field sensor)").append("\n");
                    break;
                case Sensor.TYPE_ORIENTATION:
                    //SensorManager.getOrientation();
                    sb.append(s.getType()).append(" 方向传感器(Orientation sensor)").append("\n");
                    break;
                case Sensor.TYPE_PRESSURE:
                    sb.append(s.getType()).append(" 气压传感器(Pressure sensor)").append("\n");
                    break;
                case Sensor.TYPE_PROXIMITY:
                    sb.append(s.getType()).append(" 距离传感器(Proximity sensor)").append("\n");
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    sb.append(s.getType()).append(" 温度传感器(Temperature sensor)").append("\n");
                    break;
                default:
                    sb.append(s.getType()).append(" 其他传感器").append("\n");
                    break;
            }
            sb.append("设备名称：").append(s.getName()).append("\n 设备版本：").append(s.getVersion()).append("\n 供应商：").append(s.getVendor()).append("\n\n");
        }
        txt_show.setText(sb.toString());
    }
}