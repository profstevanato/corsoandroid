package com.example.genji.am006_activity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * A class .
 */
public class TracerActivity extends AppCompatActivity {

    static int id = 1;

    /*
     * Android advises that a deployed application should not contain logging code.
     * The Android development tools provide the BuildConfig.DEBUG flag for this purpose.
     * This flag will be automatically set to false if you export the Android application for deployment.
     * During development it will be set to true, therefore allowing you to see your logging statements
     * during development.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onCreate called");
        }
        notify("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onStart called");
        }
        notify("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onRestart called");
        }
        notify("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onResume called");
        }
        notify("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) {
            // Log.d : send a debug message
            Log.d(MyLOG.TAG, "onPause called");
        }
        notify("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onStop called");
        }
        notify("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onDestroy called");
        }
        notify("onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onRestoreInstanceState called");
        }
        notify("onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (BuildConfig.DEBUG) {
            Log.d(MyLOG.TAG, "onSaveInstanceState called");
        }
        notify("onSaveInstanceState");
    }

    private void notify(String methodName) {
        // obtain the Activity name with path
        String name = this.getClass().getName();
        String[] strings = name.split("\\.");
        // create the builder
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        // new -> image asset ... and select an icon
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(methodName + " " + strings[strings.length - 1])
                        .setContentText(name);

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotifyMgr.notify(id++, mBuilder.build());

    }
}
