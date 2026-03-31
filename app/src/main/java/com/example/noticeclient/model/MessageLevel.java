package com.example.noticeclient.model;

public enum MessageLevel {
    NORMAL,
    SUCCESS,
    WARNING,
    ERROR;

    public static MessageLevel fromString(String raw) {
        if (raw == null) {
            return NORMAL;
        }
        String value = raw.trim().toUpperCase();
        for (MessageLevel level : values()) {
            if (level.name().equals(value)) {
                return level;
            }
        }
        return NORMAL;
    }
}
