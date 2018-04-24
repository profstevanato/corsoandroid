package com.example.genji.am028_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RandomShapeView mDrawingArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawingArea = (RandomShapeView)findViewById(R.id.drawing_area);
    }

    public void redraw(View clickedButton) {
        mDrawingArea.invalidate();
    }
}
