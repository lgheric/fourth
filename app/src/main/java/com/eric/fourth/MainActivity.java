package com.eric.fourth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button on,off;
    private ImageView img_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        on = findViewById(R.id.btn_on);
        off = findViewById(R.id.btn_off);

        on.setOnClickListener(this);
        off.setOnClickListener(this);


        img_show = findViewById(R.id.img_show);
        img_show.setImageLevel(0);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_on){
            //设置的level值必须在6-10之间
            img_show.setImageLevel(10000);
        }else if (v.getId() == R.id.btn_off){
            //设置的level值必须在12-20之间
            img_show.setImageLevel(5000);
        }
    }

}