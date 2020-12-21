package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        btn_on = (Button) findViewById(R.id.btn_on);
        btn_on.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_on:
                Intent mIntent = new Intent(MainActivity.this, MainService.class);
                mIntent.putExtra(MainService.OPERATION, MainService.OPERATION_SHOW);
                startService(mIntent);
                Toast.makeText(MainActivity.this, "悬浮框已开启~", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}