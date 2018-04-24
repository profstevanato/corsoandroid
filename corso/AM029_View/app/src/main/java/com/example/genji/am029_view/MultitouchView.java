package com.example.genji.am029_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by genji on 3/11/16.
 */
public class MultitouchView extends View {

    private int[] colors = {
            ContextCompat.getColor(getContext(), R.color.blue),
            ContextCompat.getColor(getContext(), R.color.green),
            ContextCompat.getColor(getContext(), R.color.purple),
            ContextCompat.getColor(getContext(), R.color.yellow),
            ContextCompat.getColor(getContext(), R.color.grey),
            ContextCompat.getColor(getContext(), R.color.orange),
            ContextCompat.getColor(getContext(), R.color.pink),
            ContextCompat.getColor(getContext(), R.color.sea),
            ContextCompat.getColor(getContext(), R.color.red),
            ContextCompat.getColor(getContext(), R.color.black)
    };

    private static final int SIZE = 300;
    private Paint mPaint;
    private Paint textPaint;

    // a sparse array of point (x,y) of float
    private SparseArray<PointF> mActivePointers;

    public MultitouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mActivePointers = new SparseArray<PointF>();
        // Paint flag that enables antialiasing when drawing.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set painter color to a color you like
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        // Geometry and text drawn with this style will be both filled and stroked at the same time,
        // respecting the stroke-related fields on the paint.
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_size));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();

        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);

        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                // a pointer was moved
                int size = event.getPointerCount();
                for (int i = 0; i < size; i++) {
                    PointF point = mActivePointers.get(event.getPointerId(i));
                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
        }
        // redraw View
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // draw all pointers
        int size = mActivePointers.size();
        for (int i = 0; i < size; i++) {
            PointF point = mActivePointers.valueAt(i);
            if (point != null)
                mPaint.setColor(colors[i % 10]);
            canvas.drawCircle(point.x, point.y, SIZE - size*30, mPaint);
        }
        canvas.drawText("Total pointers: " + mActivePointers.size(), 10, 40, textPaint);
    }

}
