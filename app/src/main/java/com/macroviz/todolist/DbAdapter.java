package com.macroviz.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_MEMO = "memo";
    public static final String KEY_REMIND = "remind";
    public static final String KEY_BGCOLOR = "bgcolor";
    private static final String TABLE_NAME = "memo";
    private DbHelper mDbHelper;
    private SQLiteDatabase mdb;
    private final Context mCtx;
    private ContentValues values;

    public DbAdapter(Context mCtx) {
        this.mCtx = mCtx;
        open();
    }
    public void open(){
        mDbHelper = new DbHelper(mCtx);
        mdb = mDbHelper.getWritableDatabase();
        Log.i("DB=",mdb.toString());
    }
    public long createMemo(String date, String memo, String remind, String bgcolor ){
        long id = 0;
        try{
            values = new ContentValues();
            values.put(KEY_DATE, date);
            values.put(KEY_MEMO, memo);
            values.put(KEY_REMIND, remind);
            values.put(KEY_BGCOLOR, bgcolor);
            id = mdb.insert(TABLE_NAME,null,values);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            mdb.close();
            Toast.makeText(mCtx,"新增成功!", Toast.LENGTH_SHORT).show();

        }
        return id ;
    }
    public Cursor listMemos(){

        Cursor mCursor = mdb.query(TABLE_NAME, new String[]{KEY_ID, KEY_DATE,KEY_MEMO, KEY_REMIND, KEY_BGCOLOR},
        null,null,null,null,null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor queryById(int item_id){
        Cursor  mCursor = mdb.query(TABLE_NAME, new String[] {KEY_ID, KEY_DATE,KEY_MEMO, KEY_REMIND, KEY_BGCOLOR},
                KEY_ID + "=" + item_id, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        return mCursor;
    }
    public long updateMemo(int id, String date, String memo, String remind, String bgcolor){
        long update = 0;
        try{
            //將資料丟到contentValues
            ContentValues values = new ContentValues();
            values.put(KEY_DATE, date);
            values.put(KEY_MEMO, memo);
            values.put(KEY_REMIND, remind);
            values.put(KEY_BGCOLOR, bgcolor);
            update = mdb.update(TABLE_NAME, values, "_id=" + id,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Toast.makeText(mCtx, "成功更新一筆資料!",Toast.LENGTH_LONG).show();
        }
        return update;
    }
    public boolean deleteMemo(int id){
        String[] args = {Integer.toString(id)};
        mdb.delete(TABLE_NAME, "_id= ?",args);
        return true;
    }
}
