package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BlurMaskFilterView extends View {

    private BlurMaskFilter bmf,bmf2,bmf3,bmf4 = null;
    private Paint paint;
    private Bitmap bp;

    public BlurMaskFilterView(Context context) {
        super(context);init();
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);init();
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    private void init(){
        paint = new Paint();
        bmf = new BlurMaskFilter(50f,BlurMaskFilter.Blur.NORMAL);
        bmf2 = new BlurMaskFilter(10f,BlurMaskFilter.Blur.OUTER);
        bmf3 = new BlurMaskFilter(50f,BlurMaskFilter.Blur.INNER);
        bmf4 = new BlurMaskFilter(50f,BlurMaskFilter.Blur.SOLID);
        bp = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setAntiAlias(true);          //抗锯齿
        paint.setColor(Color.RED);          //画笔颜色
        paint.setStyle(Paint.Style.FILL);  //画笔风格
        paint.setTextSize(68);             //绘制文字大小，单位px
        paint.setStrokeWidth(5);           //画笔粗细

        paint.setMaskFilter(bmf);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 100, paint);

        paint.setMaskFilter(bmf2);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 200, paint);

        paint.setMaskFilter(bmf3);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 300, paint);

        paint.setMaskFilter(bmf4);
        canvas.drawText("最喜欢看曹神日狗了~", 100, 400, paint);

        paint.setMaskFilter(bmf);
        canvas.drawBitmap(bp,100,800 ,paint);

        paint.setMaskFilter(bmf2);
        canvas.drawBitmap(bp,0,0 ,paint);

        paint.setMaskFilter(bmf3);
        canvas.drawBitmap(bp,600,800 ,paint);

        paint.setMaskFilter(bmf4);
        canvas.drawBitmap(bp,800,800 ,paint);



        setLayerType(View.LAYER_TYPE_SOFTWARE, null);     //关闭硬件加速
    }
}
