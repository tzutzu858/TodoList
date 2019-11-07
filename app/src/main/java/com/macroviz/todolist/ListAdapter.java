package com.macroviz.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    LayoutInflater inflater;

    private ArrayList<Memo> memos;
    public ListAdapter(Context context, ArrayList<Memo> memos) {

        this.memos = memos;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return memos.size();
    }

    @Override
    public Object getItem(int position) {
        return memos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return memos.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Memo one_memo = (Memo) getItem(position);
        convertView =  inflater.inflate(R.layout.item_view,null);
        TextView date = convertView.findViewById(R.id.txtDate);
        TextView memo = convertView.findViewById(R.id.txtMemo);
        LinearLayout bg = convertView.findViewById(R.id.bg_memo);
        ImageView btn_del = convertView.findViewById(R.id.btnDel);
        date.setText(one_memo.getDate());
        memo.setText(one_memo.getMemo());
        bg.setBackgroundColor(Color.parseColor(one_memo.getBg_color()));

        return convertView;
    }
}
