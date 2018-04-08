package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecentOrderAdapter extends RecyclerView.Adapter<RecentOrderItemHolder> {

    private List<RecentOrder> RecentOrderList;

    public RecentOrderAdapter(List<RecentOrder> RecentOrderList) {
        this.RecentOrderList = RecentOrderList;
    }

    @Override
    public RecentOrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_order_item_layout, parent, false);

        return new RecentOrderItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentOrderItemHolder holder, int position) {
            RecentOrder item = RecentOrderList.get(position);
            holder.dateOrder.setBackground(null);
            holder.theOrders.setBackground(null);

            holder.dateOrder.setText(item.getOrderDate());
            holder.theOrders.setText(item.getFoodOrders());
        }


    @Override
    public int getItemCount() {
        return RecentOrderList.size();
    }
}