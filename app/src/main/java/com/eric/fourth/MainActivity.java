package com.eric.fourth;

import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private LevelListDrawable ld;
    private ImageView img_show;

    private final Handler handler = new Handler(Looper.myLooper()){
        public void handleMessage(Message msg){
            if(msg.what == 0x123){
                if(ld.getLevel() >10000){
                    ld.setLevel(0);
                }
                img_show.setImageLevel(ld.getLevel()+2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img_show = findViewById(R.id.img_show);
        //td = (TransitionDrawable) img_show.getDrawable();
        //td.startTransition(3000);
        //你可以可以反过来播放，使用reverseTransition即可~
        //td.reverseTransition(3000);
        ld = (LevelListDrawable) img_show.getDrawable();
        img_show.setImageLevel(0);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                    handler.sendEmptyMessage(0x123);
            }
        },0,100);
    }

    public void test(View v){
        //td.reverseTransition(3000);

    }
}