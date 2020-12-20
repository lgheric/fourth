package com.eric.fourth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_show;
    private GridLayout gp_matrix;
    private Button btn_reset;
    private Button btn_Change;
    private Bitmap mBitmap;
    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        bindViews();


        gp_matrix.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = gp_matrix.getWidth() / 5;
                mEtHeight = gp_matrix.getHeight() / 4;
                //添加5 * 4个EditText
                for (int i = 0; i < 20; i++) {
                    EditText editText = new EditText(mContext);
                    editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    mEts[i] = editText;
                    gp_matrix.addView(editText, mEtWidth, mEtHeight);
                }
                initMatrix();
            }
        });
    }

    private void bindViews() {
        img_show = findViewById(R.id.img_show);
        gp_matrix = findViewById(R.id.gp_matrix);
        btn_reset = findViewById(R.id.btn_reset);
        btn_Change = findViewById(R.id.btn_Change);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meinv9_horizontal);
        img_show.setImageBitmap(mBitmap);

        btn_reset.setOnClickListener(this);
        btn_Change.setOnClickListener(this);
    }


    //定义一个初始化颜色矩阵的方法
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    //定义一个获取矩阵值得方法
    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.parseFloat(mEts[i].getText().toString());
        }
    }

    //根据颜色矩阵的值来处理图片
    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        img_show.setImageBitmap(bmp);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Change:
                getMatrix();
                setImageMatrix();
                break;
            case R.id.btn_reset:
                initMatrix();
                getMatrix();
                setImageMatrix();
                break;
        }
    }
}

