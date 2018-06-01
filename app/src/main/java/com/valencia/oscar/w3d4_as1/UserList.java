package com.valencia.oscar.w3d4_as1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.valencia.oscar.w3d4_ex1.R;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    private static final String TAG = UserList.class.getSimpleName()+"_TAG";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        initElements();
    }

    private void initElements(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(generateList());
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<UserItem> generateList(){
        ArrayList<UserItem> list = new ArrayList<>();
        Cursor cursor = null;
        userDB = new UserDB(this,"USERS",null,1);
        SQLiteDatabase sqLiteDatabase = userDB.getWritableDatabase();
        if(null!=sqLiteDatabase){
            String SQL_SELECT = "SELECT * FROM "+ userDB.getTableName();
            Log.d(TAG,SQL_SELECT);
            try{
                cursor = sqLiteDatabase.rawQuery(SQL_SELECT, null);
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    UserItem userItem = new UserItem(
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn9())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn10())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn11())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn13())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn23())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn21())),
                            cursor.getString(cursor.getColumnIndex(userDB.getColumn16()))
                    );
                    list.add(userItem);
                    cursor.moveToNext();
                }
            }catch(Exception e){
                Log.d(TAG,e.toString());
            }finally {
                cursor.close();
                sqLiteDatabase.close();
            }
        }else{
            Log.d(TAG,"DB was not created");
        }
        return list;

    }
}
