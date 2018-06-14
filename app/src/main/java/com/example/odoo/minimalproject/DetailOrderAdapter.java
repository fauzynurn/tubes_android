package com.example.odoo.minimalproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 6/11/18.
 */

public class DetailOrderAdapter extends RecyclerView.Adapter<DetailOrderItemHolder> {
    List<String> menuList = new ArrayList<>();

    public DetailOrderAdapter(List<String> menuList){
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public DetailOrderItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_order_item_layout, parent, false);
        return new DetailOrderItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailOrderItemHolder holder, int position) {
        String item = menuList.get(position);
        holder.menuName.setText(item);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}
