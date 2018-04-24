package com.example.genji.am016_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProductsDataSource pds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pds = new ProductsDataSource(this);
    }

    public void startOperate(View view){
        Intent i = new Intent(this, OperateActivity.class);
        startActivity(i);
    }

    public void initialize(View view){
        // DB transaction
        pds.open();
        pds.initialize();
        pds.close();
    }

    public void clearDB(View view){
        // DB transaction
        pds.open();
        pds.deleteAll();
        pds.close();
    }
}
