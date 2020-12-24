package com.eric.fourth;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Button btnOne = new Button(this);
        btnOne.setText("我是动态添加的按钮");
        btnOne.setTextColor(Color.BLUE);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        lp.addRule(RelativeLayout.VISIBLE);

        //RelativeLayout rly = (RelativeLayout) findViewById(R.id.RelativeLayout1);
        //rly.addView(btnOne,lp2);


        LayoutInflater inflater = LayoutInflater.from(this);
        RelativeLayout rly = (RelativeLayout) inflater.inflate(R.layout.activity_main, null).findViewById(R.id.RelativeLayout1);
        rly.addView(btnOne,lp);


        setContentView(rly);

    }

}