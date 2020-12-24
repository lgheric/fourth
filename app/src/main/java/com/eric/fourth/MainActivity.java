package com.eric.fourth;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1,获取LayoutInflater 对象
        final LayoutInflater inflater = LayoutInflater.from(this);

        //2,获得外部容器对象
        final RelativeLayout rly = (RelativeLayout) findViewById(R.id.RelativeLayout1);

        //给外部容器里的button对象添加监听事件
        Button btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得要添加的布局对象
                LinearLayout ly = (LinearLayout) inflater.inflate(R.layout.inflate, null, false).findViewById(R.id.ly_inflate);

                //设置加载布局的大小
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                //设置加载布局的位置
                lp.addRule(RelativeLayout.CENTER_IN_PARENT);
                rly.addView(ly,lp);


            }
        });

    }

}