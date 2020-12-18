package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class EmbossMaskFilterView extends View {

    private final float[] direction = new float[]{ 1, 1, 3 };   // 设置光源的方向;
    private EmbossMaskFilter emboss;
    private final Paint paint = new Paint();
    private final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.after1);


    public EmbossMaskFilterView(Context context) {
        super(context);init();
    }

    public EmbossMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);init();
    }

    public EmbossMaskFilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }

    private void init(){
        float light = 0.4f;     //设置环境光亮度
        float specular = 8;     // 定义镜面反射系数
        float blur = 3.0f;      //模糊半径
        emboss=new EmbossMaskFilter(direction,light,specular,blur);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setAntiAlias(true);          //抗锯齿
        paint.setColor(Color.BLUE);//画笔颜色
        paint.setStyle(Paint.Style.FILL);  //画笔风格
        paint.setTextSize(70);             //绘制文字大小，单位px
        paint.setStrokeWidth(8);           //画笔粗细
        //paint.setMaskFilter(emboss);

        paint.setMaskFilter(emboss);
        canvas.drawText("最喜欢看曹神日狗了~", 50, 100, paint);


        canvas.drawBitmap(bitmap, 150, 200, paint);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);     //关闭硬件加速
    }
}