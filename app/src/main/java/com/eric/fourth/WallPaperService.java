package com.eric.fourth;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;

public class WallPaperService extends Service {
    private int current = 0;  //当前壁纸下标
    private int[] papers = new int[]{
            R.mipmap.pre1,R.mipmap.pre2,R.mipmap.pre3,R.mipmap.pre4,R.mipmap.pre5,
            R.mipmap.pre6,R.mipmap.pre7,R.mipmap.pre8,R.mipmap.pre9,R.mipmap.pre10,
            R.mipmap.pre11,R.mipmap.pre12,R.mipmap.pre13,R.mipmap.pre14,R.mipmap.pre15,
            R.mipmap.pre16,R.mipmap.pre17,R.mipmap.pre18,R.mipmap.pre19,R.mipmap.pre20,R.mipmap.pre21
    };
    private WallpaperManager wManager = null;   //定义WallpaperManager服务

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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(current >= 20)current = 0;
        try{
            wManager.setResource(papers[current++]);
        }catch(Exception e){e.printStackTrace();}
        return START_STICKY;
    }

}