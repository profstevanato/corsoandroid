package com.example.genji.am024_threading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// FIRST Handler approach

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**** the same as ****
         * handler = new Handler(Looper.getMainLooper()){...}
         */
        handler = new Handler() {

            // when the thread send a messa to handler ....
            @Override
            public void handleMessage(Message msg) {
                TextView myTextView =
                        (TextView)findViewById(R.id.myTextView);
                myTextView.setText("Button Pressed");
            }
        };
    }

    public void buttonClick(View view) {

        Runnable runnable = new Runnable() {
            public void run() {

                long endTime = System.currentTimeMillis() + 10*1000;

                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {}
                    }

                }
                // send a message wich contains only a number reference
                handler.sendEmptyMessage(0);
            }
        };

        Thread mythread = new Thread(runnable);
        mythread.start();

    }
}
