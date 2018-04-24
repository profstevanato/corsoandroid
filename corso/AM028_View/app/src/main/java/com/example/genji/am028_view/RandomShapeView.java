package com.example.genji.am028_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by genji on 2/25/16.
 */
public class RandomShapeView extends View {

    private Integer[] mBackgrounds =  {
            ContextCompat.getColor(super.getContext(), R.color.myBlue),
            ContextCompat.getColor(super.getContext(), R.color.myGreen),
            ContextCompat.getColor(super.getContext(), R.color.myGrey),
            ContextCompat.getColor(super.getContext(), R.color.myOrange),
            ContextCompat.getColor(super.getContext(), R.color.myPurple),
            ContextCompat.getColor(super.getContext(), R.color.myYellow)

    };

    private Paint[] mForegrounds = {
            makePaint(ContextCompat.getColor(super.getContext(), R.color.myFgBlue)),
            makePaint(ContextCompat.getColor(super.getContext(), R.color.myFgYellow)),
            makePaint(ContextCompat.getColor(super.getContext(), R.color.myFgGreen)),
            makePaint(ContextCompat.getColor(super.getContext(), R.color.myFgPurple)),
            makePaint(ContextCompat.getColor(super.getContext(), R.color.myFgGrey))
    };

    /* we do not use these
    private Bitmap[] mPics = {
            makeBitmap(R.drawable.anger),
            makeBitmap(R.drawable.cool),
            makeBitmap(R.drawable.question),
            makeBitmap(R.drawable.smile),
            makeBitmap(R.drawable.surprise)
    };
    */

    private String mMessage = "Android";

    /* constructor for adding programmatically

    public RandomShapeView(Context context) {
        super(context);
    }
    */

    // constructor for adding in XML

    public RandomShapeView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    // Avoid allocating objects in onDraw, especially bitmaps.

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(RandomUtils.randomElement(mBackgrounds));
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int avgShapeWidth = viewWidth/6;
        for(int i=0; i<20; i++) {
            drawRandomCircle(canvas, viewWidth, viewHeight, avgShapeWidth);
            drawRandomRect(canvas, viewWidth, viewHeight, avgShapeWidth);
            //drawRandomBitmap(canvas, viewWidth, viewHeight);
            drawRandomText(canvas, viewWidth, viewHeight, avgShapeWidth);
        }
    }

    private Paint makePaint(int color) {
        Paint p = new Paint();
        p.setColor(color);
        return(p);
    }
    /* we do not use this
    private Bitmap makeBitmap(int bitmapId) {
        return(BitmapFactory.decodeResource(getResources(), bitmapId));
    }
    */

    private void drawRandomCircle(Canvas canvas, int viewWidth,
                                  int viewHeight, int avgShapeWidth) {
        float x = RandomUtils.randomFloat(viewWidth);
        float y = RandomUtils.randomFloat(viewHeight);
        float radius = RandomUtils.randomFloat(avgShapeWidth/2);
        Paint circleColor = RandomUtils.randomElement(mForegrounds);
        canvas.drawCircle(x, y, radius, circleColor);
    }

    private void drawRandomRect(Canvas canvas, int viewWidth,
                                int viewHeight, int avgShapeWidth) {
        float left = RandomUtils.randomFloat(viewWidth);
        float top = RandomUtils.randomFloat(viewHeight);
        float width = RandomUtils.randomFloat(avgShapeWidth);
        float right = left + width;
        float bottom = top + width;
        Paint squareColor = RandomUtils.randomElement(mForegrounds);
        canvas.drawRect(left, top, right, bottom, squareColor);
    }

    /* we do not use this
    private void drawRandomBitmap(Canvas canvas, int viewWidth,
                                  int viewHeight) {
        float left = RandomUtils.randomFloat(viewWidth/2);
        float top = RandomUtils.randomFloat(viewHeight/2);
        Bitmap pic = RandomUtils.randomElement(mPics);
        // Last arg is the Paint: you can use null for opaque images
        canvas.drawBitmap(pic, left, top, null);
    }
    */

    private void drawRandomText(Canvas canvas, int viewWidth,
                                int viewHeight, int avgShapeWidth) {
        float x = RandomUtils.randomFloat(viewWidth);
        float y = RandomUtils.randomFloat(viewHeight);
        float textSize = RandomUtils.randomFloat(avgShapeWidth);
        Paint textPaint = RandomUtils.randomElement(mForegrounds);
        textPaint.setTextSize(textSize);
        canvas.drawText(mMessage, x, y, textPaint);
    }
}
