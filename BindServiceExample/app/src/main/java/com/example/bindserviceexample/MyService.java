package com.example.bindserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    MyBinder myBinder = new MyBinder();

    public MyService() {
    }

    public void ShowLog(){
        Log.d("Service","LOGLOGLOG");
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service","onCreate");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Service","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service","onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("Service","onBind");
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    class MyBinder extends Binder{
        public void operate(){
            ShowLog();
        }
    }
}
