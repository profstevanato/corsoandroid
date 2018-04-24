package com.example.genji.am040_service;

import android.app.IntentService;
import android.content.Intent;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HelloIntentService extends IntentService {

    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "HelloIntentService starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            String msg = intent.getStringExtra(PARAM_IN_MSG);
            Thread.sleep(5000);
            String resultTxt = msg + " "
                    + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
            // processing done here….
            Intent broadcastIntent = new Intent();
            // set intent action
            broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
            // set cathegory
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            // add message
            broadcastIntent.putExtra(PARAM_OUT_MSG, resultTxt);
            // send intent to broadcast
            sendBroadcast(broadcastIntent);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
}
