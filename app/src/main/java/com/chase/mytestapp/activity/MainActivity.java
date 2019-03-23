package com.chase.mytestapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.chase.mytestapp.R;
import com.chase.mytestapp.adapter.MenuAdatper;
import com.chase.mytestapp.adapter.MyAdapter;
import com.chase.mytestapp.utils.LoadDialog;
import com.chase.mytestapp.view.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }


    /**
     * 初始化布局
     */
    @SuppressLint({ "InlinedApi", "InflateParams" })
    private void initView() {

    }

    /**
     * 添加监听器
     */
    private void setListener() {
        findViewById(R.id.bt_blueTooth).setOnClickListener(this);
        findViewById(R.id.bt_httpServer).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_blueTooth:
                startActivity(new Intent(this, BlueToothTestActivity.class));
                break;
            case R.id.bt_httpServer:
                startActivity(new Intent(this, httpSeverActivity.class));
                break;
        }

    }
}
