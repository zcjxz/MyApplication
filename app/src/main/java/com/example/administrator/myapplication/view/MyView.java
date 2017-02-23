package com.example.administrator.myapplication.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    public final int MODE_POINT=0;
    public final int MODE_PATH=1;
    private Paint paint;
    private Path path;
    private ArrayList<Point> points;
    private ArrayList<Path> paths;
    private boolean isPoint=true;
    private boolean isPath=false;


    public void setMode(int mode){
        if (mode==MODE_PATH){
            isPath=true;
            isPoint=false;
        }else if(mode==MODE_POINT){
            isPath=false;
            isPoint=true;
        }
    }

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
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        points=new ArrayList<>();
        paths=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if (path!=null){
            canvas.drawPath(path,paint);
        }
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            canvas.drawPoint(point.x,point.y,paint);
        }
        Iterator<Path> pathIterator = paths.iterator();
        while (pathIterator.hasNext()){
            Path path = pathIterator.next();
            canvas.drawPath(path,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isPoint) {
            points.add(new Point((int) event.getX(), (int) event.getY()));
            postInvalidate();
            return true;
        }
        if (isPath){
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path=new Path();
                    path.moveTo(x,y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);
                    postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    paths.add(path);
                    paint=null;
                    break;
            }
            return true;
        }
        return true;
    }
    private void debug(String log){
        Log.i("zcj",log);
    }
}
