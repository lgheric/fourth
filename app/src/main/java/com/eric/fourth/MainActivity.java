package com.eric.fourth;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv_phone1;
    private TextView tv_phone2;
    private TextView tv_phone3;
    private TextView tv_phone4;
    private TextView tv_phone5;
    private TextView tv_phone6;
    private TextView tv_phone7;
    private TextView tv_phone8;
    private TextView tv_phone9;
    private TelephonyManager tManager;
    private String[] phoneType = {"未知", "2G", "3G", "4G"};
    private String[] simState = {"状态未知", "无SIM卡", "被PIN加锁", "被PUK加锁",
            "被NetWork PIN加锁", "已准备好"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //①获得系统提供的TelphonyManager对象的实例
        tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        bindViews();
    }

    @SuppressLint({"SetTextI18n", "HardwareIds"})
    private void bindViews() {
        tv_phone1 = (TextView) findViewById(R.id.tv_phone1);
        tv_phone2 = (TextView) findViewById(R.id.tv_phone2);
        tv_phone3 = (TextView) findViewById(R.id.tv_phone3);
        tv_phone4 = (TextView) findViewById(R.id.tv_phone4);
        tv_phone5 = (TextView) findViewById(R.id.tv_phone5);
        tv_phone6 = (TextView) findViewById(R.id.tv_phone6);
        tv_phone7 = (TextView) findViewById(R.id.tv_phone7);
        tv_phone8 = (TextView) findViewById(R.id.tv_phone8);
        tv_phone9 = (TextView) findViewById(R.id.tv_phone9);

        tv_phone1.setText("设备编号：" + tManager.getDeviceId());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        tv_phone2.setText("软件版本：" + (tManager.getDeviceSoftwareVersion() != null ? tManager.getDeviceSoftwareVersion() : "未知"));
        tv_phone3.setText("运营商代号：" + tManager.getNetworkOperator());
        tv_phone4.setText("运营商名称：" + tManager.getNetworkOperatorName());
        tv_phone5.setText("网络类型：" + phoneType[tManager.getPhoneType()]);
        tv_phone6.setText("设备当前位置：" + (tManager.getCellLocation() != null ? tManager.getCellLocation().toString() : "未知位置"));
        tv_phone7.setText("SIM卡的国别：" + tManager.getSimCountryIso());
        tv_phone8.setText("SIM卡序列号：" + tManager.getSimSerialNumber());
        tv_phone9.setText("SIM卡状态：" + simState[tManager.getSimState()]);
    }
}