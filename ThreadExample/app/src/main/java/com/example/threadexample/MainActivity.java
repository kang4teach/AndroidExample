package com.example.threadexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button,handlerBtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.threadBtn);
        textView = findViewById(R.id.textView);
        Log.d("Thread",String.valueOf(Thread.currentThread().getId()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        textView.setText("更新TextView111");
                        };
                }).start();
            }
        });
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                switch (msg.what){
                    case 1:
                        textView.setText("Hello,Handler");
                        break;
                    case 2:
                        textView.setText(msg.obj.toString());
                        break;
                }
            }
        };
        handlerBtn = findViewById(R.id.handerBtn);
        handlerBtn.setOnClickListener((view) -> {
           new Thread(new Runnable() {
               @Override
               public void run() {
                   Message msg = new Message();
                   msg.what = 2;
                   msg.obj = "handler,handler";
                   handler.sendMessage(msg);
               }
           }).start();
        });
    }

    private int Sum100()
    {
        int sum = 0;
        try {
            for (int i =0;i<=10;i++){
                Thread.sleep(100);
                sum += i;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
