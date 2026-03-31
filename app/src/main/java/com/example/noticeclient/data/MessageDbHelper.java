package com.example.noticeclient.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 本地SQLite数据库：保存消息历史。
 */
public class MessageDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "notice.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_MESSAGE = "message";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_CONTENT = "content";
    public static final String COL_TIME = "timestamp";
    public static final String COL_LEVEL = "level";

    public MessageDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_MESSAGE + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT NOT NULL,"
                + COL_CONTENT + " TEXT NOT NULL,"
                + COL_TIME + " INTEGER NOT NULL,"
                + COL_LEVEL + " TEXT NOT NULL"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 当前版本无需升级逻辑
    }
}
