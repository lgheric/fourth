package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mInflater = LayoutInflater.from(this);
        initData();
        initView();
    }

    private void initData() {
        mImgIds = new int[] { R.mipmap.after1, R.mipmap.after2, R.mipmap.after3,R.mipmap.after4,
                R.mipmap.after5,R.mipmap.after6,R.mipmap.after7,R.mipmap.after8,R.mipmap.after9
        };
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        mGallery = findViewById(R.id.id_gallery);

        for (int mImgId : mImgIds) {
            View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                    mGallery, false);
            ImageView img = view.findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgId);
            TextView txt = view.findViewById(R.id.id_index_gallery_item_text);
            txt.setText("some info");
            txt.setTextColor(Color.BLACK);
            mGallery.addView(view);
        }
    }
}

