package com.example.odoo.minimalproject;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.victor.loading.rotate.RotateLoading;

/**
 * Created by odoo on 4/4/18.
 */

public class loading extends Dialog implements View.OnClickListener{
    public Activity a;
    private TextView name;
    private String text;
    RotateLoading rl;
    public loading(Activity c, String x) {
        super(c);
        this.a =  c;
        text = x;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading);
        rl = findViewById(R.id.rotateloading);
    }

    public void playLoading(){
        rl.start();
    }

    @Override
    public void onClick(View view) {

    }
}