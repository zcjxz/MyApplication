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


public class DrawView extends View implements DrawViewInterface{
    public final int MODE_POINT=0;
    public final int MODE_PATH=1;
    private Paint mPaint;
    private Path mPath;
    private ArrayList<Point> points;
    private ArrayList<Path> paths;
    private int mode =MODE_PATH;

    public void setMode(int mode){
        this.mode =mode;
    }

    @Override
    public void undo() {
        if (mode==MODE_POINT){
            if (points.size()>0) {
                points.remove(points.size() - 1);
            }
        }else if (mode==MODE_PATH){
            if (paths.size()>0) {
                paths.remove(paths.size() - 1);
            }
        }
        postInvalidate();
    }

    @Override
    public void advance() {

    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mPaint =new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        points=new ArrayList<>();
        paths=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if (mPath !=null){
            canvas.drawPath(mPath, mPaint);
            debug("mPath != null");
        }
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()){
            Point point = iterator.next();
            canvas.drawPoint(point.x,point.y, mPaint);
        }
        debug("paths.size= "+paths.size());
        Iterator<Path> pathIterator = paths.iterator();
        while (pathIterator.hasNext()){
            Path path = pathIterator.next();
            canvas.drawPath(path, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        debug("onTouch");
        if (mode ==MODE_POINT) {
            points.add(new Point((int) event.getX(), (int) event.getY()));
            postInvalidate();
            debug("isPoint");
            return true;
        }
        if (mode==MODE_PATH){
            debug("isPath");
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    mPath =new Path();
                    mPath.moveTo(x,y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPath.lineTo(x,y);
                    postInvalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    paths.add(mPath);
                    mPath =null;
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
