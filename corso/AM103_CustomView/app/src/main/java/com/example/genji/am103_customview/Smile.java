package com.example.genji.am103_customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by genji on 3/27/17.
 */

public class Smile extends View {

    private int mColor;
    private int mScale;
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private RectF mArcBounds = new RectF();

    public Smile(Context context) {
        this(context, null);
    }

    public Smile(Context context, AttributeSet attrs) {
        super(context, attrs);
        // get attribute value
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Smile,
                0, 0);
        try {
            // 0 -> white, 1 -> yellow ...
            mColor = a.getInt(R.styleable.Smile_smileColor, 0);
            // 0 -> no scale ...
            mScale = a.getInt(R.styleable.Smile_smileScale, 0);
        } finally {
            a.recycle();
        }
        // init paint
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        switch(mColor){
            case 0: mCirclePaint.setColor(Color.WHITE);
                break;
            case 1: mCirclePaint.setColor(Color.YELLOW);
                break;
            case 2: mCirclePaint.setColor(Color.GREEN);
                break;
            case 3: mCirclePaint.setColor(Color.RED);
                break;
        }
        mEyeAndMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint.setStyle(Paint.Style.STROKE);
        mEyeAndMouthPaint.setStrokeWidth(4 * getResources().getDisplayMetrics().density);
        mEyeAndMouthPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyeAndMouthPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // draw face
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        canvas.drawCircle(mCenterX, mCenterY, mRadius - 2*getResources().getDisplayMetrics().density, mEyeAndMouthPaint);
        // draw eyes
        float eyeRadius = mRadius / 8f;
        float eyeOffsetX = mRadius / 3f;
        float eyeOffsetY = mRadius / 3f;
        canvas.drawCircle(mCenterX - eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
        canvas.drawCircle(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
        // draw mouth
        float mouthInset = mRadius / 3f;
        mArcBounds.set(mouthInset, mouthInset, mRadius * 2 - mouthInset, mRadius * 2 - mouthInset);
        canvas.drawArc(mArcBounds, 45f, 90f, false, mEyeAndMouthPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size;
        if(mScale == 0){ // the size is obtain only  from layout (see layout_weight)
            int w = MeasureSpec.getSize(widthMeasureSpec);
            int h = MeasureSpec.getSize(heightMeasureSpec);
            size = Math.min(w, h);
        } else {
            // // the size from getSuggestedMinimumWidth() -> in XML android:minWidth
            int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
            size = resolveSizeAndState(minw/mScale, widthMeasureSpec, 0);
        }
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
    }
}
