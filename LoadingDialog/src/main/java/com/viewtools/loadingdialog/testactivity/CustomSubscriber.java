package com.viewtools.loadingdialog.testactivity;

import rx.Subscriber;

/**
 * Created by zhangll on 2016/11/7.
 */

public interface CustomSubscriber<T> {
    Subscriber<T> subscribe();
}
