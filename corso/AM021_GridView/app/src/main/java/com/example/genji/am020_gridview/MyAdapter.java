package com.example.genji.am020_gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by genji on 2/14/16.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public MyAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // LayoutInflater inflater = LayoutInflater.from(context);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView;

        if (convertView == null) {

            itemView = new View(context);

            // get layout from mobile.xml
            itemView = inflater.inflate(R.layout.item, null);

            // set value into textview
            TextView textView = (TextView) itemView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) itemView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

            if (mobile.equals("Windows")) {
                imageView.setImageResource(R.drawable.windows_logo);
            } else if (mobile.equals("IOS")) {
                imageView.setImageResource(R.drawable.ios_logo);
            } else if (mobile.equals("Linux")) {
                imageView.setImageResource(R.drawable.linux_logo);
            } else {
                imageView.setImageResource(R.drawable.android_logo);
            }

        } else {
            itemView = (View) convertView;
        }

        return itemView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
