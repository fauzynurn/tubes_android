package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by odoo on 6/11/18.
 */

public class DetailOrderItemHolder extends RecyclerView.ViewHolder {
    public TextView menuName;

    public DetailOrderItemHolder(View view){
        super(view);
        menuName = view.findViewById(R.id.menu_name);
    }
}
