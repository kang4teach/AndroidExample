package com.example.asynctaskexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button btn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        final MyAsyncTask myAsyncTask = new MyAsyncTask();
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                myAsyncTask.execute();
            }
        });
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAsyncTask.cancel(true);
            }
        });
    }

    // AsyncTask

    class MyAsyncTask extends AsyncTask<Integer,Integer,Integer>{
        @Override
        protected Integer doInBackground(Integer... integers) {
            // 完成异步任务的操作函数
            int progress = 5;
            while (progress < 100){
                try {
                    Thread.sleep(1000);
                    progress += 5;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(progress);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            // publishProgerss 触发 进度更新时调用
            progressBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(Integer integer) {
            // doInBackground完成返回后 调用
            textView.setText("任务完成！");
            progressBar.setProgress(100);
        }

        @Override
        protected void onPreExecute() {
            // execute()开始时调用
            textView.setText("任务开始！");
            progressBar.setProgress(5);
        }

        /**
         * <p>Runs on the UI thread after {@link #cancel(boolean)} is invoked and
         * {@link #doInBackground(Object[])} has finished.</p>
         *
         * <p>The default implementation simply invokes {@link #onCancelled()} and
         * ignores the result. If you write your own implementation, do not call
         * <code>super.onCancelled(result)</code>.</p>
         *
         * @param integer The result, if any, computed in
         *                {@link #doInBackground(Object[])}, can be null
         * @see #cancel(boolean)
         * @see #isCancelled()
         */
        @Override
        protected void onCancelled(Integer integer) {
            textView.setText("任务取消！");
        }

    }



}
