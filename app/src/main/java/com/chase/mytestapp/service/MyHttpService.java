package com.chase.mytestapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyHttpService extends Service {
    public static final String TAG = "MyTestService";

    private MyHttpServer myHttpServer;
    private boolean bServerStarted;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        startHttpSever();
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        stopHttpSever();
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        bServerStarted=false;
        myHttpServer = new MyHttpServer(8080);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        startHttpSever();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        stopHttpSever();
        super.onDestroy();
    }

    private void startHttpSever() {
        if(myHttpServer != null && !bServerStarted) {
            try {
                myHttpServer.start();
                bServerStarted = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return;
    }

    private void stopHttpSever() {
        if(myHttpServer != null) {
            try {
                myHttpServer.stop();
                bServerStarted = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
