package com.chase.mytestapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, intent.getAction().toString());

        if(intent.getExtras()==null)
            return;

        if(intent.getAction().equals("com.chase.mytestapp.receiver.myReceiver")) {
            String POSCMD = intent.getExtras().getString("POSCMD");
            String DATA = intent.getExtras().getString("DATA");
            Log.d(TAG, "POSCMD:" + POSCMD);
            Log.d(TAG, "DATA:" + DATA);
        }

    }
}
