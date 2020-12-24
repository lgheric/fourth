package com.eric.fourth;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_set_wallpaper = (Button) findViewById(R.id.btn_set_wallpaper);

        btn_set_wallpaper.setOnClickListener(new View.OnClickListener(){

            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                WallpaperManager wManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wManager.setResource(R.mipmap.pre5);
                    Toast.makeText(getApplicationContext(),"壁纸设置成功。",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Button btn_select_wallpaper = (Button) findViewById(R.id.btn_select_wallpaper);

        btn_select_wallpaper.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent chooseIntent = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(Intent.createChooser(chooseIntent, "选择壁纸"));
            }
        });
    }

}