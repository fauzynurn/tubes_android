package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by odoo on 5/14/18.
 */

public class MenuPreviewItemHolder extends RecyclerView.ViewHolder{
    public LinearLayout ll;
    public TextView menuPreview;

    public MenuPreviewItemHolder(View view){
        super(view);
        ll = view.findViewById(R.id.linear_layout);
        ll.setBackgroundResource(R.drawable.rounded_shape);
        menuPreview = view.findViewById(R.id.the_preview);
    }
}
