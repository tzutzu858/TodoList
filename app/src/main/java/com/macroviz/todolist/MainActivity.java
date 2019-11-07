package com.macroviz.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;
    TextView no_memo;
    ListView list_memos;
    int item_id;
    private Intent intent;
    private ListAdapter dataSimpleAdapter;
    ArrayList<Memo> memos = new ArrayList<>();
    Cursor cursor;
    private AlertDialog dialog = null;
    AlertDialog.Builder builder = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DbAdapter(this);
        Log.i("dbCount=",String.valueOf(dbAdapter.listMemos().getCount()));
        no_memo = findViewById(R.id.no_memo);
        list_memos = findViewById(R.id.list_memos);
        //判斷目前是否有聯絡人資料並設定顯示元件，如果是0，就顯示「目前無資料」
        if(dbAdapter.listMemos().getCount() == 0){
            list_memos.setVisibility(View.INVISIBLE);
            no_memo.setVisibility(View.VISIBLE);
        }else{
            list_memos.setVisibility(View.VISIBLE);
            no_memo.setVisibility(View.INVISIBLE);
        }
        displayList();
        list_memos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item_position = parent.getItemAtPosition(position);
                cursor.move(position);
                item_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

                intent = new Intent();
                intent.putExtra("item_id", item_id);
                intent.putExtra("type","edit");
                intent.setClass(MainActivity.this, EditMemoActivity.class);
                startActivity(intent);
            }
        });
        list_memos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.move(position);
                item_id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                dialog = builder.create();
                dialog.show();

                return true;
            }
        });
        builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("訊息")
                .setMessage("確定刪除此便條?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    //設定確定按鈕
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        boolean is_deleted = dbAdapter.deleteMemo(item_id);
                        if(is_deleted) {
                            Toast.makeText(MainActivity.this, "已刪除!", Toast.LENGTH_SHORT).show();
                            memos = new ArrayList<>();
                            displayList();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    //設定取消按鈕
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
    }
    private void displayList(){
        cursor = dbAdapter.listMemos();

        if(cursor != null){
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            do{
                memos.add(new Memo(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)));

            }while(cursor.moveToNext());
        }
        cursor.moveToFirst();
        dataSimpleAdapter = new ListAdapter(this,memos);
        list_memos.setAdapter(dataSimpleAdapter);

    }

    //充氣機,menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //按下icon跳轉到新增menu的頁面
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent i = new Intent(this, EditMemoActivity.class);
                i.putExtra("type","add");
                startActivity(i);

                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
