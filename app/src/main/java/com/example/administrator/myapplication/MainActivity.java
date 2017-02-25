package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.view.DrawView;
import com.example.administrator.myapplication.view.MySurfaceView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final int MODE_POINT=0;
    public final int MODE_PATH=1;
    private MySurfaceView drawView;
//    private Button btnLine;
    private int type=MODE_POINT;
    private Button btnUndo;
    private Button btnAdvance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (MySurfaceView) findViewById(R.id.myView);
//        btnLine = (Button) findViewById(R.id.btn_switch);
        btnUndo = (Button) findViewById(R.id.btn_undo);
//        btnLine.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnAdvance = (Button) findViewById(R.id.btn_advance);
        btnAdvance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btn_switch:
//                if (type==MODE_PATH){
//                    type=MODE_POINT;
//                    btnLine.setText("画线");
//                }else if (type==MODE_POINT){
//                    type=MODE_PATH;
//                    btnLine.setText("画点");
//                }
//                drawView.setMode(type);
//                break;
            case R.id.btn_undo:
                drawView.undo();
                break;
            case R.id.btn_advance:
                drawView.advance();
        }
    }
}
