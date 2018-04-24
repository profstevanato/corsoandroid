package com.example.genji.am040_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Bundle extras = getIntent().getExtras();
        String msg;

        if (extras != null) {
            msg = extras.getString("msg");
            ((TextView)findViewById(R.id.result)).setText(msg);
        }
        */
    }

    public void startService(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String strInputMsg = editText.getText().toString();
        Intent intent = new Intent(this, HelloIntentService.class);
        intent.putExtra(HelloIntentService.PARAM_IN_MSG, strInputMsg);
        startService(intent);
    }
}
