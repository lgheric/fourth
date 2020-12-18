package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MeiziAdapter extends BaseAdapter {


    private Context mContext;
    private int[] mData;

    public MeiziAdapter() {
    }

    public MeiziAdapter(Context mContext,int[] mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gallery.LayoutParams lp = new Gallery.LayoutParams(250, 250);
        //水平滚动的小部件不要再用Gallery了，改为HorizontalScrollView

        ImageView imgMezi = new ImageView(mContext);
        imgMezi.setImageResource(mData[position]);         //创建一个ImageView
        imgMezi.setScaleType(ImageView.ScaleType.FIT_XY);      //设置imgView的缩放类型
        imgMezi.setLayoutParams(lp);    //为imgView设置布局参数
        //@SuppressLint("Recycle") TypedArray typedArray = mContext.obtainStyledAttributes(R.styleable.Gallery);
        //imgMezi.setBackgroundResource(typedArray.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 0));
        //imgMezi.setBackgroundResource(R.drawable.fruits);
        return imgMezi;
    }
}
