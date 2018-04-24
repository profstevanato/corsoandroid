package com.example.genji.am040_service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by genji on 3/10/16.
 */
public class ResponseReceiver extends BroadcastReceiver {
    public static final String ACTION_RESP =
            "com.example.genji.am040_service.MESSAGE_PROCESSED";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "HelloIntentService: " + intent.getStringExtra(HelloIntentService.PARAM_OUT_MSG), Toast.LENGTH_SHORT).show();
        /*
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("msg", intent.getStringExtra(HelloIntentService.PARAM_OUT_MSG));
        context.startActivity(i);
        */
    }
}
