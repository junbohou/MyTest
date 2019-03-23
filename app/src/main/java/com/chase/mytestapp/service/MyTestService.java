package com.chase.mytestapp.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyTestService extends Service {
    public static final String TAG = "MyTestService";
    public static String ACTION_INTENT_RECEIVER = "com.chase.mytestapp.myActivityReciver";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        sendMsg("onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind");
        sendMsg("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        sendMsg("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        sendMsg("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        sendMsg("onDestroy");
        super.onDestroy();
    }

    private void sendMsg(String msg) {
        Intent intenttest = new Intent(ACTION_INTENT_RECEIVER);
        intenttest.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intenttest.putExtra("POSCMD", "PRINT"); // 交易指令
        intenttest.putExtra("DATA", msg);
        sendBroadcast(intenttest);

        Intent intent2 = new Intent("com.chase.mytestapp.receiver.myReceiver");
        intent2.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent2.putExtra("POSCMD", "PRINT"); // 交易指令
        intent2.putExtra("DATA", msg);
        sendBroadcast(intent2);
    }

}
