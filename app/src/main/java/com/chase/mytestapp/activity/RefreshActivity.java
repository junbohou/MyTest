package com.chase.mytestapp.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.chase.mytestapp.R;
import com.chase.mytestapp.adapter.MyAdapter;
import com.chase.mytestapp.utils.LoadDialog;
import com.chase.mytestapp.view.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class RefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener {

    private RefreshLayout swipeLayout;
    private ListView listView;
    private MyAdapter adapter;
    private ArrayList<HashMap<String, String>> list;
    private View header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        //调用module中的loadingdialog
//        LoadingDialog ld = new LoadingDialog(this);
////        ld.setInterceptBack(false)
////                .setLoadingText("加载中...")
////                .setLoadStyle(0)
////                .show();

        //启动module中的MainTestActivity
//        startActivity(new Intent(this, MainTestActivity.class));

        initView();
        setData();
        setListener();
		/*设置自动刷新 swipeLayout.setRefreshing(true);
		在 setRefreshing(true)并没有触发onRefresh的,须要手动调用一次
        */
//		swipeLayout.post(new Thread(new Runnable() {
//			@Override
//			public void run() {
//				swipeLayout.setRefreshing(true);
//			}
//		}));
//		onRefresh();
    }


    /**
     * 初始化布局
     */
    @SuppressLint({ "InlinedApi", "InflateParams" })
    private void initView() {
        header = getLayoutInflater().inflate(R.layout.header, null);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setColorSchemeResources(R.color.color_bule2,R.color.color_bule,R.color.color_bule2,R.color.color_bule3);
    }

    /**
     * 添加数据
     */
    private void setData() {
        list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("itemImage", i+"默认");
            map.put("itemText", i+"默认");
            list.add(map);
        }
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(header);
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据  更新完后调用该方法结束刷新
                list.clear();
                for (int i = 0; i < 8; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("itemImage", i+"刷新");
                    map.put("itemText", i+"刷新");
                    list.add(map);
                }
                adapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 2000);

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 更新数据  更新完后调用该方法结束刷新
                swipeLayout.setLoading(false);
                for (int i = 1; i < 10; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("itemImage", i+"更多");
                    map.put("itemText", i+"更多");
                    list.add(map);
                }
                adapter.notifyDataSetChanged();
            }
        }, 2000);
    }




    @Override
    protected void onResume() {
        super.onResume();

        //单例模式
        LoadDialog.getLoadDialog().show(RefreshActivity.this, null);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LoadDialog.getLoadDialog().close();

            }
        }).start();

        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }
}
