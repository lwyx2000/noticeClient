package com.example.noticeclient.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noticeclient.model.MessageLevel;
import com.example.noticeclient.model.NoticeMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息读写仓库。
 */
public class MessageRepository {

    private final MessageDbHelper dbHelper;

    public MessageRepository(Context context) {
        dbHelper = new MessageDbHelper(context.getApplicationContext());
    }

    public long insert(NoticeMessage message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MessageDbHelper.COL_TITLE, message.getTitle());
        values.put(MessageDbHelper.COL_CONTENT, message.getContent());
        values.put(MessageDbHelper.COL_TIME, message.getTimestamp());
        values.put(MessageDbHelper.COL_LEVEL, message.getLevel().name());
        return db.insert(MessageDbHelper.TABLE_MESSAGE, null, values);
    }

    public List<NoticeMessage> queryAll() {
        List<NoticeMessage> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                MessageDbHelper.TABLE_MESSAGE,
                null,
                null,
                null,
                null,
                null,
                MessageDbHelper.COL_TIME + " DESC"
        );
        try {
            while (cursor.moveToNext()) {
                NoticeMessage msg = new NoticeMessage();
                msg.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MessageDbHelper.COL_ID)));
                msg.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MessageDbHelper.COL_TITLE)));
                msg.setContent(cursor.getString(cursor.getColumnIndexOrThrow(MessageDbHelper.COL_CONTENT)));
                msg.setTimestamp(cursor.getLong(cursor.getColumnIndexOrThrow(MessageDbHelper.COL_TIME)));
                msg.setLevel(MessageLevel.fromString(cursor.getString(cursor.getColumnIndexOrThrow(MessageDbHelper.COL_LEVEL))));
                list.add(msg);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    public int deleteById(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(MessageDbHelper.TABLE_MESSAGE, MessageDbHelper.COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int clearAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(MessageDbHelper.TABLE_MESSAGE, null, null);
    }
}
