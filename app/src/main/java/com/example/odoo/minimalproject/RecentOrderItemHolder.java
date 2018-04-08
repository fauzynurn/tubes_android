package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class RecentOrderItemHolder extends RecyclerView.ViewHolder {

    public TextView dateOrder, theOrders;
    public ShimmerFrameLayout shimmerFrameLayout;

    public RecentOrderItemHolder(View view) {
        super(view);
        shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.fl_shimmer);
        dateOrder = (TextView) view.findViewById(R.id.date_order);
        theOrders = (TextView) view.findViewById(R.id.the_orders);
    }
}
