package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class RecentOrderItemHolder extends RecyclerView.ViewHolder {

    public TextView dateOrder, theOrders, status;

    public RecentOrderItemHolder(View view) {
        super(view);
        dateOrder = view.findViewById(R.id.ordered_date);
        theOrders = view.findViewById(R.id.menu_name);
        status = view.findViewById(R.id.status);
    }
}
