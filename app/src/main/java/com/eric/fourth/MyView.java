package com.eric.fourth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView  extends View {

    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    private void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(60);

        canvas.translate(300,300);
        canvas.clipRect(100, 100, 300, 300);                //设置显示范围
        canvas.drawColor(Color.BLACK);                      //白色背景
        canvas.drawText("双11，继续吃我的狗粮...", 150, 300, mPaint); //绘制字符串
    }
}
