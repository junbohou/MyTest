package com.chase.mytestapp.service;

import android.util.Log;

import com.chase.mytestapp.MyApp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;


public class MyHttpServer extends NanoHTTPD {
    public String TAG = "MyHttpServer";

    public MyHttpServer(int port) {
        super(port);
    }

    @Override
    public Response serve(IHTTPSession session) {

        MyApp.getInstance().sendMsg("url:" + session.getUri());


//        String msg = "<html><body><h1>Hello server</h1>\n";
//        Map<String, String> parms = session.getParms();
//        if (parms.get("username") == null) {
//            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
//        } else {
//            Log.i(TAG, "username: " + parms.get("username"));
//            msg += "<p>Hello, " + parms.get("username") + "!</p>";
//        }
//
//        return newFixedLengthResponse(msg + "</body></html>\n");

        try {
            // 这一句话必须要写，否则在获取数据时，获取不到数据
            session.parseBody(new HashMap<String, String>());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        Method method = session.getMethod();
        Map<String, String> parms = session.getParms();
        String data = parms.get("data");//这里的data是POST提交表单时key
        Log.i(TAG, "data: "+data);

        StringBuilder builder = new StringBuilder();
        builder.append("hello myHttpServer");// 反馈给调用者的数据
        return newFixedLengthResponse(builder.toString());

    }
}
