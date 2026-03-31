package com.example.noticeclient.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noticeclient.R;
import com.example.noticeclient.data.MessageRepository;
import com.example.noticeclient.model.NoticeMessage;
import com.example.noticeclient.util.AppConstants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MessageAdapter.OnItemActionListener {

    private MessageRepository repository;
    private MessageAdapter adapter;
    private TextView emptyView;

    private final BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            loadMessages();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new MessageRepository(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.tvEmpty);
        TextView clearAll = findViewById(R.id.btnClearAll);
        FloatingActionButton settingBtn = findViewById(R.id.btnSetting);

        adapter = new MessageAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        clearAll.setOnClickListener(v -> showClearDialog());
        settingBtn.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(refreshReceiver, new IntentFilter(AppConstants.ACTION_NEW_MESSAGE));

        loadMessages();
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(refreshReceiver);
        super.onDestroy();
    }

    private void loadMessages() {
        List<NoticeMessage> list = repository.queryAll();
        adapter.setData(list);
        emptyView.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void showClearDialog() {
        new AlertDialog.Builder(this)
                .setTitle("清空确认")
                .setMessage("确认删除所有消息记录？")
                .setPositiveButton("确认", (dialog, which) -> {
                    repository.clearAll();
                    loadMessages();
                })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public void onItemClick(NoticeMessage message) {
        Intent intent = new Intent(this, MessageDetailActivity.class);
        intent.putExtra(AppConstants.EXTRA_MESSAGE_ID, message.getId());
        intent.putExtra(AppConstants.EXTRA_TITLE, message.getTitle());
        intent.putExtra(AppConstants.EXTRA_CONTENT, message.getContent());
        intent.putExtra(AppConstants.EXTRA_TIME, message.getTimestamp());
        intent.putExtra(AppConstants.EXTRA_LEVEL, message.getLevel().name());
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(NoticeMessage message) {
        repository.deleteById(message.getId());
        loadMessages();
    }
}
