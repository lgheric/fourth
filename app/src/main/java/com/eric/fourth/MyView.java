package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class MyView  extends View {

    private Bitmap mBitmap;
    private Matrix matrix = new Matrix();
    private float sx = 0.0f;          //设置倾斜度
    private int width,height;         //位图宽高
    private float scale = 1.0f;       //缩放比例
    private int method = 0;
    private Bitmap bitmap;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.after7);
        width = mBitmap.getWidth();
        height = mBitmap.getHeight();
        bitmap = Bitmap.createBitmap(mBitmap,0,0,width,height,matrix,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (method){
            case 0:
                matrix.reset();
                break;
            case 1:
                sx += 0.1;
                matrix.setSkew(sx,0);
                break;
            case 2:
                sx -= 0.1;
                matrix.setSkew(sx,0);
                break;
            case 3:
                if(scale < 2.0){
                    scale += 0.1;
                }
                matrix.setScale(scale,scale);
                break;
            case 4:
                if(scale > 0.5){
                    scale -= 0.1;
                }
                matrix.setScale(scale,scale);
                break;
        }
        //根据原始位图与Matrix创建新图片
        canvas.drawBitmap(bitmap,matrix,null);    //绘制新位图
    }

    public void setMethod(int i){
        method = i;
        postInvalidate();
    }
}
