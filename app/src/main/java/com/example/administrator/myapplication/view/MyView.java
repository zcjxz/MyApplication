package com.example.administrator.myapplication.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication.R;

import java.util.ArrayList;
import java.util.Iterator;

public class MyView extends View {
    private Paint paint;
    private Path path;
    private Point point;
    private ArrayList<Point> points;


    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
        points=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            canvas.drawPoint(point.x,point.y,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        points.add(new Point((int)event.getX(),(int)event.getY()));
        invalidate();
        debug("onTouch");
        return true;
    }
    private void debug(String log){
        Log.i("zcj",log);
    }
}
