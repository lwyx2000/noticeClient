package com.example.noticeclient.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noticeclient.R;
import com.example.noticeclient.model.MessageLevel;
import com.example.noticeclient.util.AppConstants;
import com.example.noticeclient.util.TimeFormatter;

public class MessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvLevel = findViewById(R.id.tvDetailLevel);
        TextView tvTime = findViewById(R.id.tvDetailTime);
        TextView tvContent = findViewById(R.id.tvDetailContent);

        String title = getIntent().getStringExtra(AppConstants.EXTRA_TITLE);
        String content = getIntent().getStringExtra(AppConstants.EXTRA_CONTENT);
        long time = getIntent().getLongExtra(AppConstants.EXTRA_TIME, System.currentTimeMillis());
        MessageLevel level = MessageLevel.fromString(getIntent().getStringExtra(AppConstants.EXTRA_LEVEL));

        tvTitle.setText(title);
        tvContent.setText(content);
        tvTime.setText(TimeFormatter.format(time));
        tvLevel.setText(level.name());
    }
}
