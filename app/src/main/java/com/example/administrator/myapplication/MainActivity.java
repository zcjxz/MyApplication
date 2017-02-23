package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.view.MyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final int MODE_POINT=0;
    public final int MODE_PATH=1;
    private MyView myView;
    private Button btnLine;
    private int type=MODE_POINT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.myView);
        btnLine = (Button) findViewById(R.id.btn_switch);
        btnLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_switch:
                if (type==MODE_PATH){
                    type=MODE_POINT;
                }else if (type==MODE_POINT){
                    type=MODE_PATH;
                }
                myView.setMode(type);
                break;
        }
    }
}
