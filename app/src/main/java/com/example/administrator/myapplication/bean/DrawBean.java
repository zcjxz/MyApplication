package com.example.administrator.myapplication.bean;


import android.graphics.Paint;
import android.graphics.Path;

public class DrawBean {

    public DrawBean(Paint mPaint, Path mPath) {
        this.mPaint = mPaint;
        this.mPath = mPath;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    public Path getmPath() {
        return mPath;
    }

    public void setmPath(Path mPath) {
        this.mPath = mPath;
    }

    private Paint mPaint;
    private Path mPath;
}
