package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/29/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartItemHolder> {
    private List<MenuForCart> cartList;

    public CartAdapter(ArrayList<MenuForCart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);

        return new RecentOrderItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartItemHolder holder, int position) {
        MenuForCart item = cartList.get(position);
        holder.cartMenu.setText(item.getMeal());
        holder.desc.setText(item.getDesc());
        holder.totprice.setText(item.getTotalPriceMenu());
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
