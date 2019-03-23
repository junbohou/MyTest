package com.chase.mytestapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.chase.mytestapp.R;
import com.chase.mytestapp.adapter.MenuAdatper;
import com.chase.mytestapp.adapter.MyAdapter;
import com.chase.mytestapp.utils.BytesUtils;
import com.chase.mytestapp.view.RefreshLayout;
import com.smartpos.mrs2btlib.MagicRs232toBt;

import java.util.ArrayList;
import java.util.HashMap;

public class BlueToothTestActivity extends Activity implements View.OnClickListener {
    public String TAG = "BlueTest";

    private TextView et_content;
    private Button bt_open;
    private Button bt_send;
    private Button bt_read;
    private Button bt_close;

    private MagicRs232toBt rs232btDevice;

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
        String name = "MRS2BT-000001";
        rs232btDevice = new MagicRs232toBt(name);

        if(rs232btDevice == null) {
            showText("获取对象失败");
        } else {
            showText("获取对象成功");
        }
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
                open();
                break;
            case R.id.bt_send:
                send();
                break;
            case R.id.bt_read:
                read();
                break;
            case R.id.bt_close:
                close();
                break;
        }
    }


    private void open() {
        int res = rs232btDevice.connect();
        showText("连接蓝牙：" + res);
    }

    private void send() {
        String test = "test";
        byte[] bytes = test.getBytes();
        int res = rs232btDevice.write(bytes);
        showText("写数据：" + res);
    }

    private void read() {
        byte[] bytes = new byte[100];
        int res = rs232btDevice.read(bytes);

        showText("接收数据：" + BytesUtils.bcdToString(bytes));
    }

    private void close() {
        int res = rs232btDevice.close();
        showText("关闭蓝牙：" + res);
    }

    private void showText(String text) {
        String str = et_content.getText().toString();
        et_content.setText(text + "\n" + str);
    }
}
