package com.eric.fourth;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            WindowManager wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wManager.getDefaultDisplay().getMetrics(dm);
            Toast.makeText(MainActivity.this, "当前手机的屏幕宽高：" + dm.widthPixels + "*" +
                    dm.heightPixels, Toast.LENGTH_SHORT).show();
        }
}