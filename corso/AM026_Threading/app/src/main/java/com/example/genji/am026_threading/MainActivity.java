package com.example.genji.am026_threading;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// <uses-permission android:name="android.permission.INTERNET" /> added in manifest

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TextView01);
    }

    /* generic:
     * <params> the type of the parameters sent to the task upon execution.
     * <progress> the type of the progress units published during the background computation (Es Integer).
     * <result>, the type of the result of the background computation.
     */

    private class DownloadWebPageTask extends AsyncTask<String, Void, String> {

        // return <result>, <param> ... argument
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            for (String url : urls) {
                URL reqUrl = null;
                HttpURLConnection request = null;
                try {
                    reqUrl = new URL(url);
                    request = (HttpURLConnection) (reqUrl.openConnection());
                    /* 
                    InputStream bufferIn = new BufferedInputStream(request.getInputStream());
                    byte[] contents = new byte[1024]; // size of buffer
                    int bytesRead = 0; // start position
                    while ((bytesRead = bufferIn.read(contents)) != -1) {
                        response += new String(contents, 0, bytesRead);
                    }
                    */
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }
                } catch (MalformedURLException e) { // url
                    e.printStackTrace();
                } catch (IOException e) { // openConnection()
                    e.printStackTrace();
                } finally { // disconnect request
                    request.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            textView.setText(result);
        }
    }

    public void onClick(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[]{"http://www.zuccante.it"});
    }

}


