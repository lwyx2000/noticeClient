package com.example.noticeclient.app;

import android.app.Application;
import android.content.Intent;

import com.example.noticeclient.service.KeepAliveService;

import cn.jpush.android.api.JPushInterface;

/**
 * 全局Application：用于初始化JPush和后台保活服务。
 */
public class NoticeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        Intent keepAliveIntent = new Intent(this, KeepAliveService.class);
        startService(keepAliveIntent);
    }
}
