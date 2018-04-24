package com.example.genji.am004_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        Toast.makeText(context,
                "Received broadcast in MyBroadcastReceiver, \n " +
                        " value received: " + i.getStringExtra("key"),
                Toast.LENGTH_LONG).show();
    }
}
