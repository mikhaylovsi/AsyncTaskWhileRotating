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

        mt = (MyTask)getLastCustomNonConfigurationInstance();
        if(mt == null) {
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);
        Log.d("qwe", "create MyTask: " + mt.hashCode());
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String, Integer, Void>{

        MainActivity activity;

        void link(MainActivity act){
            activity = act;
        }

        void unLink(){
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try{
                for(int i = 1; i <= 10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("qwe", "i = " + i + ", MyTask: " + this.hashCode()
                    + ", MainActivity: " + activity.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }



        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.tv.setText("i = " + values[0]);

        }
    }
}
