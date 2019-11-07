package com.macroviz.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//建構子
public class DbHelper extends SQLiteOpenHelper {
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_MEMO = "memo";
    public static final String KEY_REMIND = "remind";
    public static final String KEY_BGCOLOR = "bgcolor";
    public static final String DATABASE_NAME = "Memos";
    public static final String TABLE_NAME = "memo";
    public static final int DB_VERSION = 1;
    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //組裝建立資料表的SQL語法
        final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                KEY_DATE + "," +
                KEY_MEMO + "," +
                KEY_REMIND + "," +
                KEY_BGCOLOR + ");";
        //執行sql語法
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //刪除舊資料表
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //重新建立新資料表
        onCreate(db);
    }
}
