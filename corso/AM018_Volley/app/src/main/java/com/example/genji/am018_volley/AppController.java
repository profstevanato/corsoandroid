package com.example.genji.am018_volley;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/** OLD AND ORIGINAL VERSION
 *
 * There is normally no need to subclass Application.
 * In most situation, static singletons can provide the same functionality in a more modular way.
 * If your singleton needs a global context (for example to register broadcast receivers),
 * the function to retrieve it can be given a Context which internally uses
 * Context.getApplicationContext() when first constructing the singleton.
 *
 * Created by genji on 2/11/16.
 * singleton pattern for queue
 *
 * add in manifest, application
 *
 * android:name=".AppController"
 * 
 * Application is itself a singleton
 */
public class AppController extends Application {
    // obtain the name of the class (not with package path)
    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    // tag is used to cancell requests (here one tag, all the requests)
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
