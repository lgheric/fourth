package com.eric.fourth;

import android.annotation.SuppressLint;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SeekBar mSeekbar;
    private TextView mTvShow;
    private ImageView mImageShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekbar = findViewById(R.id.seekbar);
        mTvShow = findViewById(R.id.tv_info);
        mImageShow = findViewById(R.id.iv_show);

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int max = seekBar.getMax();
                double scale = (double) progress / (double) max;
                ClipDrawable drawable = (ClipDrawable) mImageShow.getBackground();
                drawable.setLevel((int) (10000 * scale));
                mTvShow.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}