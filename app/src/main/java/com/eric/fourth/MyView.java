package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView  extends View {

    private Paint mPaint;
    private Bitmap myBitMap;
    private RectF myRectF,myRectF2;
    private Path path;
    private Paint citePaint;
    private Paint tmpPaint;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.yellow,null));
//        canvas.drawCircle(400, 400, 100, mPaint);           //画实心圆
//        canvas.drawRect(0, 0, 200, 100, mPaint);            //画矩形
//        canvas.drawRect(200, 200, 400, 300, mPaint);            //画矩形
//        canvas.drawRect(400,200,600,300, mPaint);            //画矩形
//        canvas.drawBitmap(myBitMap, 400, 500, mPaint);
//        canvas.drawArc(myRectF,0,90,true,mPaint);  //绘制弧形区域
//        canvas.drawRoundRect(myRectF,30,30,mPaint); //画圆角矩形
//
//        canvas.drawOval(myRectF2,mPaint); //画椭圆
//
//        path.moveTo(1500, 1600); //移动到 坐标10,10
//        path.lineTo(100, 50);
//        path.lineTo(200,40);
//        path.lineTo(300, 20);
//        path.lineTo(200, 10);
//        path.lineTo(100, 70);
//        path.lineTo(50, 40);
//        path.close();
//        canvas.drawPath(path,mPaint);

//        canvas.drawText("最喜欢看曹神日狗了~",100,100,mPaint);    //绘制文字
//
//        path.moveTo(50,50);
//        path.lineTo(100, 100);
//        path.lineTo(200, 200);
//        path.lineTo(300, 300);
//        path.close();
//        canvas.drawTextOnPath("最喜欢看曹神日狗了~", path, 50, 50, mPaint);    //绘制文字

        canvas.translate(canvas.getWidth()/2, 200); //将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100, mPaint); //画圆圈

        //使用path绘制路径文字
        canvas.save();
        canvas.translate(-75, -75);
        //Path path = new Path();
        path.addArc(myRectF, -180, 180);
        //Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(14);
        citePaint.setStrokeWidth(1);
        canvas.drawTextOnPath("绘制表盘~", path, 28, 0, citePaint);
        canvas.restore();

        //Paint tmpPaint = new Paint(mPaint); //小刻度画笔对象
        tmpPaint.setStrokeWidth(1);

        float  y=100;
        int count = 60; //总刻度数

        for(int i=0 ; i <count ; i++){
            if(i%5 == 0){
                canvas.drawLine(0f, y, 0, y+12f, mPaint);
                canvas.drawText(String.valueOf(i/5+1), -4f, y+25f, tmpPaint);

            }else{
                canvas.drawLine(0f, y, 0f, y +5f, tmpPaint);
            }
            canvas.rotate(360/count,0f,0f); //旋转画纸
        }

        //绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7, tmpPaint);
        tmpPaint.setStyle(Paint.Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5, tmpPaint);
        canvas.drawLine(0, 10, 0, -65, mPaint);
    }

    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);          //抗锯齿
        mPaint.setColor(getResources().getColor(R.color.purple,null));//画笔颜色
        mPaint.setStyle(Paint.Style.FILL);  //画笔风格
        mPaint.setTextSize(36);             //绘制文字大小，单位px
        mPaint.setStrokeWidth(5);           //画笔粗细

        //不要在自定义View的onMeasure、onLayout、onDraw等方法里面做new对象的操作。
        //因为实例化对象是会耗性能的，而这几个方法会被多次调用，所以需要将对象作为属性，
        // 在初始化的时候就实例化好对象，在这些方法里面直接用就行了
        //
        myBitMap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        myRectF = new RectF(0, 0, 150, 150);
        //myRectF2 = new RectF(800, 1000, 1000, 1300);
        path = new Path();
        citePaint = new Paint(mPaint);
        tmpPaint = new Paint(mPaint);

    }
}
