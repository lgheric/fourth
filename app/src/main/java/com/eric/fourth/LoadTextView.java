package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LoadTextView extends View {

    private PorterDuffXfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Bitmap backBitmap;
    private Paint mPaint;
    private int mBitW, mBitH;
    private int mCurW, mCurH, mCurTop;
    private Rect mDynamicRect;


    public LoadTextView(Context context) {
        super(context);init();
    }

    public LoadTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs); init();
    }

    public LoadTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LoadTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        //画笔初始化：
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        //背部图片的初始化
        backBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_string);
        mBitH = backBitmap.getHeight();
        mBitW = backBitmap.getWidth();
        //设置当前的高度
        mCurTop = mBitH;
        mDynamicRect = new Rect(0, mBitH, mBitW, mBitH);  //初始化原图
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //int saveLayerCount = canvas.saveLayer(0, 0, mCurW, mCurH, mPaint, Canvas.ALL_SAVE_FLAG);
        int saveLayerCount = canvas.save();
        canvas.drawBitmap(backBitmap, 0, 0, mPaint);// 绘制目标图
        mPaint.setXfermode(mXfermode);    //设置混排模式
        canvas.drawRect(mDynamicRect, mPaint);   //绘制源图
        mPaint.setXfermode(null);         //清除混排模式
        canvas.restoreToCount(saveLayerCount);    //恢复保存的图层
        // 改变Rect区域，假如
        mCurTop -= 2;
        if (mCurTop <= 0) {
            mCurTop = mBitH;
        }
        mDynamicRect.top = mCurTop;
        invalidate();    //重绘
    }

}
