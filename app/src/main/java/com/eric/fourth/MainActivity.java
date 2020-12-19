package com.eric.fourth;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_reset;
    private Button btn_left;
    private Button btn_right;
    private Button btn_zoomin;
    private Button btn_zoomout;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        btn_reset = findViewById(R.id.btn_reset);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_zoomin = findViewById(R.id.btn_zoomin);
        btn_zoomout = findViewById(R.id.btn_zoomout);
        myView = findViewById(R.id.myView);


        btn_reset.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_zoomin.setOnClickListener(this);
        btn_zoomout.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_reset:
                myView.setMethod(0);
                break;
            case R.id.btn_left:
                myView.setMethod(1);
                break;
            case R.id.btn_right:
                myView.setMethod(2);
                break;
            case R.id.btn_zoomin:
                myView.setMethod(3);
                break;
            case R.id.btn_zoomout:
                myView.setMethod(4);
                break;
        }
    }
}

