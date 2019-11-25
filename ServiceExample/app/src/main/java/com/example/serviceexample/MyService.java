package com.example.serviceexample;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int startId;

    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SERVICE", "onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId = startId;
        Log.d("SERVICE", "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    public void Operatation() {
        Log.d("SERVICE", "Operatation");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SERVICE", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("SERVICE", "onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("SERVICE", "onUnbind");
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder {
        public void operate() {
            Operatation();
        }
    }
}