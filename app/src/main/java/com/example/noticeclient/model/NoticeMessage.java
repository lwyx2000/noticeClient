package com.example.noticeclient.model;

public class NoticeMessage {
    private long id;
    private String title;
    private String content;
    private long timestamp;
    private MessageLevel level;

    public NoticeMessage() {
    }

    public NoticeMessage(String title, String content, long timestamp, MessageLevel level) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MessageLevel getLevel() {
        return level;
    }

    public void setLevel(MessageLevel level) {
        this.level = level;
    }
}
