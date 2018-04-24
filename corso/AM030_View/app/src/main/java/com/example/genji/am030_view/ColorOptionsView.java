package com.example.genji.am030_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by genji on 3/13/16.
 */
public class ColorOptionsView extends LinearLayout {
    private View mValue;
    private ImageView mImage;

    public ColorOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Retrieve a set of basic attribute values from an AttributeSet, ...
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ColorOptionsView);
        // note underscore sintax
        String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
        int valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor,
                ContextCompat.getColor(context, R.color.blue));
        // Recycles the TypedArray, to be re-used by a later caller. After calling this function you must not ever touch the typed array again.
        a.recycle();

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_color_options, this, true);

        TextView title = (TextView) getChildAt(0);
        title.setText(titleText);

        mValue = getChildAt(1);
        mValue.setBackgroundColor(valueColor);

        mImage = (ImageView) getChildAt(2);
    }

    public ColorOptionsView(Context context) {
        this(context, null);
    }

    public void setValueColor(int color) {
        mValue.setBackgroundColor(color);
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
