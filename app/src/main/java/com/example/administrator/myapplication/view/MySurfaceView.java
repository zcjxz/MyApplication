package com.example.administrator.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bean.DrawBean;

import java.util.ArrayList;
import java.util.Iterator;


public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable,DrawViewInterface{
    //画笔的颜色
    private int paintColor;
    //画笔的宽度
    private int paintWith;

    //绘图线程
    private Thread mThread;
    //是否绘图
    private boolean isRunning;
    //路径的集合
    private ArrayList<DrawBean> drawList;
    //当前的路径
    private Path mPath;
    //当前的画笔
    private Paint mPaint;
    private SurfaceHolder holder;

    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paintColor=R.color.colorAccent;
        paintWith=10;
        drawList=new ArrayList<>();
        holder = getHolder();
        holder.addCallback(this);
        // 设置常亮
        this.setKeepScreenOn(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPaint = getPaint();
                mPath =new Path();
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                saveDraw(mPaint,mPath);
                mPath=null;
                mPaint=null;
                break;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning=true;
        mThread=new Thread(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning=false;
    }

    @Override
    public void run() {
        while (isRunning){
            draw();
        }
    }

    private void draw(){
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        if (mPaint!=null&&mPath!=null) {
            canvas.drawPath(mPath, mPaint);
        }
        Iterator<DrawBean> iterator = drawList.iterator();
        while (iterator.hasNext()){
            DrawBean drawBean = iterator.next();
            canvas.drawPath(drawBean.getmPath(),drawBean.getmPaint());
        }
        holder.unlockCanvasAndPost(canvas);

    }

    private Paint getPaint(){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(paintColor));
        paint.setStrokeWidth(paintWith);
        return paint;
    }

    @Override
    public void undo() {

    }

    /**
     * 保存路径和画笔
     */
    private void saveDraw(Paint paint,Path path){
        drawList.add(new DrawBean(paint,path));
    }

    private int getColor(int id){
        return getResources().getColor(id);
    }
}
