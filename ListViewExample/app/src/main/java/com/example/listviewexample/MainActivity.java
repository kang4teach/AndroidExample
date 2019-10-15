package com.example.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<NameClass> list = new LinkedList<>();
        int lines = 1000;
        while(lines-- > 0) {
            list.add(new NameClass("name:", "xiaoming"));
            list.add(new NameClass("name:", "xiaohong"));
        }
        NameAdapter nameAdapter = new NameAdapter(this,R.layout.list_item_layout,list);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(nameAdapter);
        // ListView 点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NameClass nameClass = list.get(i);
                Toast.makeText(
                        view.getContext(),
                        nameClass.getFirst()+nameClass.getSecond(),
                        Toast.LENGTH_SHORT
                        )
                        .show();
            }
        });


    }
}
