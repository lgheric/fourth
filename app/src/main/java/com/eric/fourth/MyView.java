package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView  extends View {

    private Paint mPaint;  //绘制线条的Path
    private Path mPath;      //记录用户绘制的Path
    private Canvas mCanvas;  //内存中创建的Canvas
    private Bitmap mBitmap;  //缓存绘制的内容
    private Bitmap bmp;  //缓存绘制的内容

    private int mLastX;
    private int mLastY;
    private Rect rect;
    private RectF bounds;
    public MyView(Context context) {
        super(context);init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

//    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);init();
//    }
//

    private void init(){
        mPath = new Path();
        mPaint = new Paint();   //初始化画笔
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND); //结合处为圆角
        mPaint.setStrokeCap(Paint.Cap.ROUND); // 设置转弯处为圆角
        mPaint.setStrokeWidth(5);   // 设置画笔宽度

        // 初始化bitmap,Canvas
        int width = 600;//getMeasuredWidth();
        int height = 600;//getMeasuredHeight();

        System.out.println("Width:"+width+" Height:"+height);
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
        rect = new Rect(50,0,150,50);
        bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        bounds = new RectF(0, 0, 400, 400);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //重写该方法，在这里绘图
    @Override
    protected void onDraw(Canvas canvas) {
        //drawPath();
        //canvas.drawBitmap(mBitmap, 0, 0, null);
//        for(int i=0; i < 5; i++) {
//            canvas.drawCircle(50, 50, 50, mPaint);
//            canvas.translate(100, 100);
//        }

//        canvas.translate(200, 200);
//        for(int i = 0; i < 36;i++){
//            canvas.rotate(10);
//            canvas.drawRect(rect, mPaint);
//        }

//        canvas.drawBitmap(bmp,0,0,mPaint);
//        canvas.scale(0.8f, 0.8f);
//        canvas.drawBitmap(bmp, 0, 0, mPaint);
//        canvas.scale(0.8f, 0.8f);
//        canvas.drawBitmap(bmp,0,0,mPaint);


//        canvas.drawBitmap(bmp,0,0,mPaint);
//        canvas.translate(200, 200);
//        canvas.skew(0.26f,0);
//        canvas.drawBitmap(bmp,0,0,mPaint);


        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.save();
        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.translate(400, 400);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        canvas.save();
        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.rotate(45);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        canvas.save();
        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.rotate(45);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        canvas.save();
        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.translate(0, 200);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        canvas.restoreToCount(3);
        System.out.println("当前层数："+canvas.getSaveCount());
        canvas.translate(0, 200);
        canvas.drawBitmap(bmp, 0, 0, mPaint);

//
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        canvas.translate(0, 200);
//        canvas.drawBitmap(bmp, 0, 0, mPaint);
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        canvas.restore();
//        System.out.println("当前层数："+canvas.getSaveCount());
//        System.out.println("当前层数："+canvas.getSaveCount());
//
//        canvas.saveLayer(bounds, mPaint);
//        System.out.println("当前层数："+canvas.getSaveCount());
        //canvas.drawColor(getResources().getColor(R.color.moss_tide,null));
        //canvas.drawBitmap(bmp, 200, 200, mPaint);
//        canvas.restoreToCount(3);
//        System.out.println("当前层数："+canvas.getSaveCount());
        //canvas.drawBitmap(bmp, 300, 200, mPaint);
    }

    //绘制线条
    private void drawPath(){
        mCanvas.drawPath(mPath, mPaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);
                mLastX = x;
                mLastY = y;
                break;
        }

        invalidate();
        return true;
    }
}
