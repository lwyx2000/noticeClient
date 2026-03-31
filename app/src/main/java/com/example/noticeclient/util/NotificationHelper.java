package com.example.noticeclient.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.noticeclient.R;
import com.example.noticeclient.model.NoticeMessage;
import com.example.noticeclient.ui.MainActivity;

/**
 * 系统通知工具：收到推送自动提醒（声音+震动）。
 */
public class NotificationHelper {
    private static final String CHANNEL_ID = "notice_message_channel";
    private static final String CHANNEL_NAME = "消息通知";

    public static void ensureChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setDescription("推送消息通知");
            manager.createNotificationChannel(channel);
        }
    }

    public static void showMessageNotification(Context context, NoticeMessage message, int notifyId) {
        ensureChannel(context);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                notifyId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(message.getTitle())
                .setContentText(message.getContent())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message.getContent()))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notifyId, notification);
    }
}
