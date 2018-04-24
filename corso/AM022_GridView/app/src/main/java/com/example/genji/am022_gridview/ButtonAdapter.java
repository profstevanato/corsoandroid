package com.example.genji.am022_gridview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by genji on 2/14/16.
 */
public class ButtonAdapter extends BaseAdapter {

    // in a class that is not an activity or a service devlare a context
    private Context mContext;

    // Gets the context so it can be used later
    public ButtonAdapter(Context c) {
        mContext = c;
    }

    // Total number of things contained within the adapter
    public int getCount() {
        return Files.NAMES.length;
    }

    // Require for structure, not really used in my code.
    public Object getItem(int position) {
        return null;
    }

    // Require for structure, not really used in my code. Can
    // be used to get the id of an item in the adapter for
    // manual control.
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position,
                        View convertView, ViewGroup parent) {
        Button btn;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            btn = new Button(mContext);
            btn.setLayoutParams(new GridView.LayoutParams(210, 140));
            btn.setPadding(4, 4, 4, 4);
        }
        else {
            btn = (Button) convertView;
        }
        btn.setText(Files.NAMES[position]);
        // filenames is an array of strings
        // btn.setTextColor(ContextCompat.getColor(mContext, R.color.colorMyGrey));
        btn.setId(position);
        btn.setOnClickListener(new MyOnClickListener(position,mContext));
        return btn;
    }
}
