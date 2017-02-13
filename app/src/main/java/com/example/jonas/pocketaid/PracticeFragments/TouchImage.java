package com.example.jonas.pocketaid.PracticeFragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Raeven on 13 Feb 2017.
 */

public class TouchImage extends ImageView {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Point point = new Point();

    public TouchImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.STROKE);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawPaint(paint);
        paint.setColor(Color.TRANSPARENT);
        paint.setStrokeWidth(15);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(point.x, point.y, 50, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point.x = event.getX();
                point.y = event.getY();
                invalidate();
        }
        return true;
    }

    class Point {
        float x, y;
    }
}