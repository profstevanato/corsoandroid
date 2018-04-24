package com.example.genji.am024_threading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

// SECOND Handler approach

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
                Bundle bundle = msg.getData();
                String string = bundle.getString("myKey");
                TextView myTextView =
                        (TextView)findViewById(R.id.myTextView);
                myTextView.setText(string);
            }
        };
    }

    public void buttonClick(View view) {

        Runnable runnable = new Runnable() {
            public void run() {
                // simulate a long operation
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                SimpleDateFormat dateformat =
                        new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
                String dateString = dateformat.format(new Date());
                bundle.putString("myKey", dateString);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        };

        Thread mythread = new Thread(runnable);
        mythread.start();
    }
}
