package com.chase.mytestapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.chase.mytestapp.R;


/**
 * Created by sun on 2017/9/4.
 */

public class LoadDialog {

    private static LoadDialog instance;
    private Dialog progressDialog;

    /**
     * 单一实例
     */
    public static LoadDialog getLoadDialog(){
        if(instance==null){
            instance=new LoadDialog();
        }
        return instance;
    }

    /**
     * 显示一个LoadDialog
     * @param activity
     */
    public void show(Activity activity){
        show(activity, null, true);
    }

    /**
     * 显示一个LoadDialog
     * @param activity
     * @param content
     */
    public void show(Activity activity, String content){
        show(activity, content, true);
    }

    /**
     * 显示一个LoadDialog
     * @param activity
     * @param content
     * @param isCancelable 是否可以取消
     */
    public void show(Activity activity, String content, boolean isCancelable){
        progressDialog = new Dialog(activity, R.style.progress_dialog);
        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCanceledOnTouchOutside(false); //点空白处不消失
        progressDialog.setCancelable(isCancelable);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
        if(content != null && content.length()>0) {
            msg.setVisibility(View.VISIBLE);
            msg.setText(content);
        } else {
            msg.setVisibility(View.GONE);
        }
        progressDialog.show();
    }

    /**
     * 销毁
     */
    public void close(){
        progressDialog.dismiss();
    }
}
