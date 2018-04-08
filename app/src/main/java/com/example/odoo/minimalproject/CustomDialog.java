package com.example.odoo.minimalproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import static android.app.Dialog.*;

/**
 * Created by odoo on 3/30/18.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{

    public TextView name;
    public Button go;
    public Button cancel;
    private String text;
    public CustomDialog(Activity c) {
        super(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void changeDefaultName(String x){
        name.setText(x);
    }

    @Override
    public void onClick(View view) {

    }
}
