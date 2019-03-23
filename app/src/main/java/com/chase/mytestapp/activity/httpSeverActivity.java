package com.chase.mytestapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chase.mytestapp.R;
import com.chase.mytestapp.service.MyHttpService;

public class httpSeverActivity extends Activity implements View.OnClickListener {
    public String TAG = "httpSeverActivity";

    private TextView et_content;
    private Button bt_open;
    private Button bt_send;
    private Button bt_read;
    private Button bt_close;

    public MessageReceiver mMessageReceiver;
    public static String ACTION_INTENT_RECEIVER = "com.chase.mytestapp.myActivityReciver";

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: " + name.getClassName());

            try {
                service.linkToDeath(new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        Log.d(TAG, "binder service is dead!!!");
                        bind();
                    }
                }, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: " + name.getClassName());
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        initView();
        initData();
        setListener();
    }


    /**
     * 初始化布局
     */
    @SuppressLint({ "InlinedApi", "InflateParams" })
    private void initView() {
        et_content = (TextView) findViewById(R.id.et_content);
        bt_open = (Button) findViewById(R.id.bt_open);
        bt_send = (Button) findViewById(R.id.bt_send);
        bt_read = (Button) findViewById(R.id.bt_read);
        bt_close = (Button) findViewById(R.id.bt_close);
    }

    private void initData() {
        registerMessageReceiver();
    }

    /**
     * 添加监听器
     */
    private void setListener() {
        bt_open.setOnClickListener(this);
        bt_send.setOnClickListener(this);
        bt_read.setOnClickListener(this);
        bt_close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open:
                start();
                break;
            case R.id.bt_close:
                stop();
                break;
            case R.id.bt_send:
                bind();
                break;
            case R.id.bt_read:
                unBind();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private void start() {
        Intent intent = new Intent(this, MyHttpService.class);
        startService(intent);
        showText("startService");
    }

    private void stop() {
        Intent intent = new Intent(this, MyHttpService.class);
        stopService(intent);
        showText("stopService");
    }

    private void bind() {
        Intent intent = new Intent(this, MyHttpService.class);
        boolean isSucc = bindService(intent, connection, Context.BIND_AUTO_CREATE);

        if(isSucc) {
            showText("bindService succ");
        } else {
            showText("bindService false");
        }
    }

    private void unBind() {
        showText("unBind");
        if(connection != null) {
            unbindService(connection);
        }
    }






    private void showText(String text) {
        String str = et_content.getText().toString();
        et_content.setText(text + "\n" + str);
    }


    private void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_INTENT_RECEIVER);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION_INTENT_RECEIVER)) {
                String POSCMD = intent.getExtras().getString("POSCMD");
                String DATA = intent.getExtras().getString("DATA");
                showText("POSCMD:" + POSCMD);
                showText("DATA:" + DATA);
            }
        }
    }
}
