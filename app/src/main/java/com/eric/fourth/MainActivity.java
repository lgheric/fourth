package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_start) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock w1 = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyTag");
            w1.acquire(60*1000L /*1 minutes*/);
            //在这个过程,屏幕会保持光亮!
            w1.release();
        }

    }
}