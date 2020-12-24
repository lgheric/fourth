package com.eric.fourth;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;

public class WallPaperService extends Service {
    private int current = 0;  //当前壁纸下标
    private int[] papers = new int[]{
            R.mipmap.pre1,R.mipmap.pre2,R.mipmap.pre3,R.mipmap.pre4,R.mipmap.pre5,
            R.mipmap.pre6,R.mipmap.pre7,R.mipmap.pre8,R.mipmap.pre9,R.mipmap.pre10,
            R.mipmap.pre11,R.mipmap.pre12,R.mipmap.pre13,R.mipmap.pre14,R.mipmap.pre15,
            R.mipmap.pre16,R.mipmap.pre17,R.mipmap.pre18,R.mipmap.pre19,R.mipmap.pre20,R.mipmap.pre21
    };
    private WallpaperManager wManager = null;   //定义WallpaperManager服务

    private final String CHANNEL_ONE_ID="one";

    public WallPaperService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        wManager = WallpaperManager.getInstance(this);
        setNotification("定时换壁纸服务进行中。。。");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(current >= 20)current = 0;
        try{
            wManager.setResource(papers[current++]);
        }catch(Exception e){e.printStackTrace();}
        return START_STICKY;
    }


    private void setNotification(String text) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification;
        NotificationManagerCompat mManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Uri mUri = Settings.System.DEFAULT_NOTIFICATION_URI;

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ONE_ID, "driver", NotificationManager.IMPORTANCE_LOW);

            mChannel.setDescription("description");

            mChannel.setSound(mUri, Notification.AUDIO_ATTRIBUTES_DEFAULT);

            mManager.createNotificationChannel(mChannel);

            notification = new Notification.Builder(this, CHANNEL_ONE_ID)
                    .setChannelId(CHANNEL_ONE_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(text)
                    .setContentIntent(pi)
                    .build();
        } else {
            // 提升应用权限
            notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(text)
                    .setContentIntent(pi)
                    .build();
        }
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        startForeground(10000, notification);
    }

    private void stopNotification(){

    }
}