package com.example.noticeclient.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.noticeclient.R;
import com.example.noticeclient.util.NotificationHelper;

/**
 * 简易前台服务保活（自用场景）。
 */
public class KeepAliveService extends Service {

    private static final int FOREGROUND_ID = 1001;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationHelper.ensureChannel(this);
        Notification notification = new NotificationCompat.Builder(this, "notice_message_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("通知服务运行中")
                .setContentText("用于保持推送接收稳定")
                .setOngoing(true)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(FOREGROUND_ID, notification);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
