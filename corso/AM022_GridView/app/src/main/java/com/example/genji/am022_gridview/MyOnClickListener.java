package com.example.genji.am022_gridview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by genji on 2/15/16.
 */
public class MyOnClickListener implements View.OnClickListener {

    private int position;
    private Context context;

    public MyOnClickListener(int position, Context context) {
        this.position = position;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(context, position + "",
                Toast.LENGTH_SHORT).show();
        Button b = (Button)v;
        b.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorMyGrey));
    }
}
