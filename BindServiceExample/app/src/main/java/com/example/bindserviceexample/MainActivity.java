package com.example.bindserviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyService.MyBinder mainActivityBinder;
    ServiceConnection myServiceConnection;
    Button bindBtn,unbindBtn,operateBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindBtn = findViewById(R.id.bindBtn);
        unbindBtn = findViewById(R.id.unbindBtn);
        operateBtn = findViewById(R.id.operateBtn);

        // 1、创建服务类型
        // 2、在服务类的内部创建内部类MyBinder 继承自Binder类，
        // MyBinder提供外部接口来调用Service类的接口，实现桥梁的功能使外部代码可以调用Service接口
        // 3、绑定服务和组件
        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建连接对象
                if (myServiceConnection == null){
                    myServiceConnection = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            Log.d("MainActivity","onServiceConnected");
                            // 在onServiceConnected事件中，将服务onBind事件中返回的Binder对象赋值到
                            // mainActivityBinder,后续可使用mainActivityBinder提供的公开接口操作服务
                            // 内的接口
                            mainActivityBinder = (MyService.MyBinder) service;
                            mainActivityBinder.operate();
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            Log.d("MainActivity","onServiceDisconnected");
                        }
                    };
                }
                // 创建显式Intent
                Intent intent = new Intent(MainActivity.this,MyService.class);
                // 调用绑定操作，
                bindService(intent,myServiceConnection,BIND_AUTO_CREATE);
            }
        });

        operateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用mainActivityBinder对象操作服务接口
                mainActivityBinder.operate();
            }
        });

        // 4、解绑服务
        unbindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainActivityBinder != null){
                    unbindService(myServiceConnection);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myServiceConnection);
    }
}
