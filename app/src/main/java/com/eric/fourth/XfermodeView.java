package com.eric.fourth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class XfermodeView extends View {

    private PorterDuffXfermode pdXfermode;   //定义PorterDuffXfermode变量
    //定义MODE常量，等下直接改这里即可进行测试
    private static PorterDuff.Mode PD_MODE = PorterDuff.Mode.DST_OUT;
    private int screenW, screenH; //屏幕宽高
    private int width = 200;      //绘制的图片宽高
    private int height = 200;
    private Bitmap srcBitmap, dstBitmap;     //上层SRC的Bitmap和下层Dst的Bitmap
    private Paint paint;

    public XfermodeView(Context context) {
        this(context, null); init();
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        super(context, attrs);init();
        screenW = ScreenUtil.getScreenW(context);
        screenH = ScreenUtil.getScreenH(context);
        //创建一个PorterDuffXfermode对象
        pdXfermode = new PorterDuffXfermode(PD_MODE);
        //实例化两个Bitmap
        srcBitmap = makeSrc(width, height);
        dstBitmap = makeDst(width, height);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init();
    }


    //定义一个绘制圆形Bitmap的方法
    private Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF26AAD1);
        c.drawOval(new RectF(0, 0, (float)w * 3 / 4, (float)h * 3 / 4), p);
        return bm;
    }

    //定义一个绘制矩形的Bitmap的方法
    private Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCE43);
        c.drawRect((float)w / 3, (float)h / 3, (float)w * 19 / 20, (float)h * 19 / 20, p);
        return bm;
    }

    private void init(){
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setFilterBitmap(false);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(srcBitmap, (float)(screenW / 3 - width) / 2, (float)(screenH / 2 - height) / 2, paint);
        canvas.drawBitmap(dstBitmap, (float)(screenW / 3 - width) / 2 + (float)screenW / 3, (float)(screenH / 2 - height) / 2, paint);
System.out.println("\n当前层"+canvas.getSaveCount());
        //创建一个图层，在图层上演示图形混合后的效果
        //int sc = canvas.saveLayer(0, 0, screenW, screenH, null);
//        int sc = canvas.save();
//        System.out.println("\n当前层"+canvas.getSaveCount());
        canvas.drawBitmap(dstBitmap, (float)(screenW / 3 - width) / 2 + (float)screenW / 3 * 2, (float)(screenH / 2 - height) / 2, paint);     //绘制i
//        //设置Paint的Xfermode
        paint.setXfermode(pdXfermode);
        canvas.drawBitmap(srcBitmap, (float)(screenW / 3 - width) / 2 + (float)screenW / 3 * 2, (float)(screenH / 2 - height) / 2, paint);
//        paint.setXfermode(null);
//        // 还原画布
//        canvas.restoreToCount(sc);System.out.println("\n当前层"+canvas.getSaveCount());
    }
}
