package com.valencia.oscar.w4d2_as1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.valencia.oscar.w3d4_ex1.R;
import com.valencia.oscar.w4d2_as1.entities.Item;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    private static final String TAG = UserList.class.getSimpleName()+"_TAG";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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
        for(Item result :MainActivity.userResponse.getItems()){
            UserItem userItem = new UserItem(
                result.getId(),
                    result.getVolumeInfo().getImageLinks().getThumbnail(),
                    result.getVolumeInfo().getTitle(),
                    result.getVolumeInfo().getSubtitle(),
                    result.getVolumeInfo().getAuthors(),
                    result.getSelfLink()
            );
            list.add(userItem);
        }
        return list;

    }
}
