package com.example.genji.am030_view;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onClicked(View view) {
        String text = view.getId() == R.id.view1 ? "First View" : "Second View";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ColorOptionsView view1 = (ColorOptionsView)findViewById(R.id.view1);
        ColorOptionsView view2 = (ColorOptionsView)findViewById(R.id.view2);

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.view1_color:
                view1.setValueColor(ContextCompat.getColor(this, R.color.blue));
                return true;
            case R.id.view1_visible:
                view1.setImageVisible(true);
                return true;
            case R.id.view1_unvisiblie:
                view1.setImageVisible(false);
                return true;
            case R.id.view2_color:
                view2.setValueColor(ContextCompat.getColor(this, R.color.orange));
                return true;
            case R.id.view2_visible:
                view2.setImageVisible(true);
                return true;
            case R.id.view2_unvisiblie:
                view2.setImageVisible(false);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
