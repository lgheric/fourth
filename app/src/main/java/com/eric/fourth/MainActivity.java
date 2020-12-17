package com.eric.fourth;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ImageView img_bg3, img_bg4, img_bg5;
    private byte[] bt;

    final Handler handler = new Handler(Objects.requireNonNull(Looper.myLooper())) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                //
                img_bg4.setImageBitmap(BitmapFactory.decodeByteArray(bt,0,bt.length));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
    }


    private void bindView() {
        ToastUtils.init(getApplication());
        ImageView img_bg = findViewById(R.id.img_show);
        ImageView img_bg2 = findViewById(R.id.img_show2);
        img_bg3 = findViewById(R.id.img_show3);
        img_bg4 = findViewById(R.id.img_show4);
        img_bg5 = findViewById(R.id.img_show5);
        try {
            BitmapDrawable bmpMeizi = new BitmapDrawable(getResources(), getAssets().open("berries.jpg"));
            Bitmap mBitmap = bmpMeizi.getBitmap();
            img_bg.setImageBitmap(mBitmap);


            Bitmap b5 = BitmapFactory.decodeStream(getAssets().open("lamp_off.png"));
            img_bg5.setImageBitmap(b5);

        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.lamp_on);
        img_bg2.setImageBitmap(b2);

        permission();

        new Thread(){
            @Override
            public void run(){
                try {
                    bt = getImage("https://www.baidu.com/img/flexible/logo/pc/result.png");
                    handler.sendEmptyMessage(0x123);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }
        public static byte[] getImage (String path) throws Exception {
            URL url = new URL(path);
            HttpURLConnection httpURLconnection = (HttpURLConnection) url.openConnection();
            httpURLconnection.setRequestMethod("GET");
            httpURLconnection.setReadTimeout(6 * 1000);
            InputStream in;
            byte[] b = new byte[1024];
            int len = -1;
            if (httpURLconnection.getResponseCode() == 200) {
                in = httpURLconnection.getInputStream();
                byte[] result = readStream(in);
                in.close();
                return result;

            }
            return null;
        }

        public static byte[] readStream (InputStream in) throws Exception {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            in.close();
            return outputStream.toByteArray();
        }


        //通过资源ID
        private Bitmap getBitmapFromResource (Resources res,int resId){
            return BitmapFactory.decodeResource(res, resId);
        }

        //文件
        private Bitmap getBitmapFromFile (String pathName){
            return BitmapFactory.decodeFile(pathName);
        }

        //字节数组
        public Bitmap Bytes2Bimap ( byte[] b){
            if (b.length != 0) {
                return BitmapFactory.decodeByteArray(b, 0, b.length);
            } else {
                return null;
            }
        }

        //输入流
        private Bitmap getBitmapFromStream (InputStream inputStream){
            return BitmapFactory.decodeStream(inputStream);
        }

        private void permission () {

            XXPermissions.with(this)
                    // 申请安装包权限
                    //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                    // 申请悬浮窗权限
                    //.permission(Permission.SYSTEM_ALERT_WINDOW)
                    // 申请通知栏权限
                    //.permission(Permission.NOTIFICATION_SERVICE)
                    // 申请系统设置权限
                    //.permission(Permission.WRITE_SETTINGS)
                    // 申请单个权限
                    .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                    // 申请单个权限
                    //.permission(Permission.RECORD_AUDIO)
                    // 申请多个权限
                    //.permission(Permission.Group.CALENDAR)
                    .request(new OnPermissionCallback() {

                        @Override
                        public void onGranted(List<String> permissions, boolean all) {
                            if (all) {
                                ToastUtils.show("获取录音和日历权限成功");
                                String fp = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/pineapple.jpg";
                                Bitmap b3 = BitmapFactory.decodeFile(fp);
                                img_bg3.setImageBitmap(b3);

                            } else {
                                ToastUtils.show("获取部分权限成功，但部分权限未正常授予");
                            }
                        }

                        @Override
                        public void onDenied(List<String> permissions, boolean never) {
                            if (never) {
                                ToastUtils.show("被永久拒绝授权，请手动授予录音和日历权限");
                                // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.startPermissionActivity(MainActivity.this, permissions);
                            } else {
                                ToastUtils.show("获取录音和日历权限失败");
                            }
                        }
                    });

        }

    }