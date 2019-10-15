package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fruit> list = new LinkedList<Fruit>();
        list.add(new Fruit(R.drawable.apple_pic,"苹果"));
        list.add(new Fruit(R.drawable.banana_pic,"香蕉"));
        list.add(new Fruit(R.drawable.cherry_pic,"樱桃"));
        list.add(new Fruit(R.drawable.grape_pic,"葡萄"));
        list.add(new Fruit(R.drawable.mango_pic,"芒果"));
        list.add(new Fruit(R.drawable.orange_pic,"橘子"));
        list.add(new Fruit(R.drawable.pear_pic,"梨"));
        list.add(new Fruit(R.drawable.strawberry_pic,"草莓"));

        // 4、先获取RecylerView 引用
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerView);

        // 5、创建布局管理器LinearLayoutManager,在recyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //6、实例化自定义的适配器，在recyclerView设置适配器
        FruitAdapter fruitAdapter = new FruitAdapter(list);
        recyclerView.setAdapter(fruitAdapter);
    }
}
