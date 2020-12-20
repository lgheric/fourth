package com.eric.fourth;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gd_show;
    private ArrayList<Data> items = null;
    private MyAdapter<Data> myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gd_show = findViewById(R.id.gd_show);

        //填充数据，遍历Mode模式：
        items = new ArrayList<Data>();
        for (PorterDuff.Mode mode : PorterDuff.Mode.class.getEnumConstants()) {
            items.add(new Data(0x77E50961, mode));
            items.add(new Data(0xFFE50961, mode));
            items.add(new Data(0x77FFFFFF, mode));
            items.add(new Data(0xFFFFFFFF, mode));
            items.add(new Data(0x77000000, mode));
            items.add(new Data(0xFF000000, mode));
        }
        myAdapter = new MyAdapter<Data>(items, R.layout.view_item) {
            @Override
            public void bindView(ViewHolder holder, Data obj) {
                holder.setColorFilter(R.id.img_show, obj.getColor(), obj.getMode());
                holder.setText(R.id.tv_color, String.format("%08X", obj.getColor()));
                holder.setText(R.id.tv_mode, obj.getMode().toString());
            }
        };
        gd_show.setAdapter(myAdapter);
    }
}