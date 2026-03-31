package com.example.noticeclient.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noticeclient.R;

import cn.jpush.android.api.JPushInterface;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tvRid = findViewById(R.id.tvRegistrationId);
        TextView btnCopy = findViewById(R.id.btnCopy);

        String rid = JPushInterface.getRegistrationID(this);
        tvRid.setText(rid == null || rid.isEmpty() ? "暂未获取到Registration ID" : rid);

        btnCopy.setOnClickListener(v -> {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("registration_id", tvRid.getText().toString());
            cm.setPrimaryClip(clipData);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
        });
    }
}
