package com.example.noticeclient.receiver;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.noticeclient.data.MessageRepository;
import com.example.noticeclient.model.MessageLevel;
import com.example.noticeclient.model.NoticeMessage;
import com.example.noticeclient.util.AppConstants;
import com.example.noticeclient.util.NotificationHelper;

import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * JPush自定义消息接收器。
 */
public class JPushMessageReceiver extends cn.jpush.android.service.JPushMessageReceiver {

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        NoticeMessage msg = parseMessage(customMessage);

        MessageRepository repository = new MessageRepository(context);
        long id = repository.insert(msg);
        msg.setId(id);

        NotificationHelper.showMessageNotification(context, msg, (int) (System.currentTimeMillis() & 0xfffffff));

        Intent intent = new Intent(AppConstants.ACTION_NEW_MESSAGE);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    private NoticeMessage parseMessage(CustomMessage customMessage) {
        String title = TextUtils.isEmpty(customMessage.title) ? "新消息" : customMessage.title;
        String content = TextUtils.isEmpty(customMessage.message) ? "无内容" : customMessage.message;
        MessageLevel level = MessageLevel.NORMAL;

        if (!TextUtils.isEmpty(customMessage.extra)) {
            try {
                JSONObject jsonObject = new JSONObject(customMessage.extra);
                level = MessageLevel.fromString(jsonObject.optString("level", "NORMAL"));
                String extraTitle = jsonObject.optString("title");
                String extraContent = jsonObject.optString("content");
                if (!TextUtils.isEmpty(extraTitle)) {
                    title = extraTitle;
                }
                if (!TextUtils.isEmpty(extraContent)) {
                    content = extraContent;
                }
            } catch (JSONException ignored) {
            }
        }
        return new NoticeMessage(title, content, System.currentTimeMillis(), level);
    }
}
