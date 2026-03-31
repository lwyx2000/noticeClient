package com.example.noticeclient.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noticeclient.R;
import com.example.noticeclient.model.MessageLevel;
import com.example.noticeclient.model.NoticeMessage;
import com.example.noticeclient.util.TimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    public interface OnItemActionListener {
        void onItemClick(NoticeMessage message);

        void onDeleteClick(NoticeMessage message);
    }

    private final List<NoticeMessage> messages = new ArrayList<>();
    private final OnItemActionListener listener;

    public MessageAdapter(OnItemActionListener listener) {
        this.listener = listener;
    }

    public void setData(List<NoticeMessage> data) {
        messages.clear();
        messages.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        NoticeMessage message = messages.get(position);
        holder.titleTv.setText(message.getTitle());
        holder.contentTv.setText(message.getContent());
        holder.timeTv.setText(TimeFormatter.format(message.getTimestamp()));
        holder.levelTv.setText(levelText(message.getLevel()));

        holder.levelTv.setBackgroundResource(levelBg(message.getLevel()));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(message));
        holder.deleteBtn.setOnClickListener(v -> listener.onDeleteClick(message));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private String levelText(MessageLevel level) {
        switch (level) {
            case SUCCESS:
                return "成功";
            case WARNING:
                return "警告";
            case ERROR:
                return "错误";
            case NORMAL:
            default:
                return "普通";
        }
    }

    private int levelBg(MessageLevel level) {
        switch (level) {
            case SUCCESS:
                return R.drawable.bg_chip_success;
            case WARNING:
                return R.drawable.bg_chip_warning;
            case ERROR:
                return R.drawable.bg_chip_error;
            case NORMAL:
            default:
                return R.drawable.bg_chip_normal;
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv;
        TextView contentTv;
        TextView timeTv;
        TextView levelTv;
        ImageButton deleteBtn;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tvTitle);
            contentTv = itemView.findViewById(R.id.tvContent);
            timeTv = itemView.findViewById(R.id.tvTime);
            levelTv = itemView.findViewById(R.id.tvLevel);
            deleteBtn = itemView.findViewById(R.id.btnDelete);
        }
    }
}
