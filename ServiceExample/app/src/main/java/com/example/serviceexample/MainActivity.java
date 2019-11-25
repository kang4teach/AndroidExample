package com.example.serviceexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileDescriptor;
import java.util.NoSuchElementException;

public class MainActivity extends AppCompatActivity {

    Button startBtn,endBtn,bindBtn ,operateBtn;
    MyService.MyBinder myBinder ;
    MyConnect myConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1、通过IDE的操作创建自定义服务的类
        // 2、修改界面布局文件，增加启动服务和停止服务的按钮
//        startBtn = findViewById(R.id.startBtn);
//        endBtn = findViewById(R.id.endBtn);
//
//        // 3、使用Intent来启动服务
//        // 首次执行的时候会调用onCreate 后续调用startService不会调用onCreate
//        // 服务运行的时候是运行在UI线程中，如果要执行耗时任务，需要使用多线程来执行避免UI阻塞
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                intent.putExtra("OPERATE","PLAY");
//                intent.putExtra("OPERATE","PAUSE");
//                intent.putExtra("OPERATE","STOP");
//                startService(intent);
//            }
//        });
//        // 4、使用Intent来停止服务
//        endBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,MyService.class);
//                stopService(intent);
//            }
//        });

        bindBtn = findViewById(R.id.bindBtn);
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 绑定Service
                if (myConnect == null){
                    myConnect = new MyConnect();
                }
                Intent intent = new Intent(MainActivity.this,MyService.class);
                bindService(intent,myConnect,BIND_AUTO_CREATE);
            }
        });
        operateBtn = findViewById(R.id.operateBtn);
        operateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBinder.operate();
            }
        });



    }

    class MyConnect implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
