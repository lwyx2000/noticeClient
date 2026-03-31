package com.example.noticeclient.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormatter {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static String format(long timestamp) {
        return FORMAT.format(new Date(timestamp));
    }
}
