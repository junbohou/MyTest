package com.chase.mytestapp;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class MyApp extends Application {

    public static String ACTION_INTENT_RECEIVER = "com.chase.mytestapp.myActivityReciver";

    private static MyApp instance;

    /**
     * 单一实例
     */
    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void sendMsg(String msg) {
        Log.d("myAPP", "sendMsg :" + msg);

        Intent intenttest = new Intent(ACTION_INTENT_RECEIVER);
        intenttest.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intenttest.putExtra("POSCMD", "PRINT"); // 交易指令
        intenttest.putExtra("DATA", msg);
        sendBroadcast(intenttest);
    }
}
