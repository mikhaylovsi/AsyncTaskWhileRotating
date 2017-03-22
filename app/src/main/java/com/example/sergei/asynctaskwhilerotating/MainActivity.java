package com.example.sergei.asynctaskwhilerotating;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    MyTask mt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("qwe", "create MainActivity " + MainActivity.this.hashCode());

        tv = (TextView)findViewById(R.id.tv);

        mt = new MyTask();
        Log.d("qwe", "create MyTask: " + mt.hashCode());
        mt.execute();
    }

    class MyTask extends AsyncTask<String, Integer, Void>{

        @Override
        protected Void doInBackground(String... params) {
            try{
                for(int i = 1; i <= 10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode()
                    + ", MainActivity: " + MainActivity.this.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText("i = " + values[0]);

        }
    }
}
