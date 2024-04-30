package com.example.fragmentlesson.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;
import java.util.logging.LogRecord;

public class CustomView extends View {
    private Paint paint;
    private int mRadius;
    private int mColor;
    private Handler handler;
    private Runnable runnable;
    public CustomView(Context context){
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
      int width = getWidth();
      int height = getHeight();
      mRadius = Math.min(width,height)/2;
      paint.setColor(mColor);
      canvas.drawCircle(width/2f, height/2f, mRadius,paint);
    }
    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        mColor = generateRandomColor();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mColor = generateRandomColor();
                invalidate();
                handler.postDelayed(this,2000);
            }
        };
        handler.post(runnable);
    }
    private int generateRandomColor(){
        Random random = new Random();
        return Color.argb(255,
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256));
    }
}

