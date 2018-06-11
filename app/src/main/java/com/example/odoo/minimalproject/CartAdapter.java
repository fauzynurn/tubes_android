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
    private List<Menu> cartList;

    public CartAdapter(List<Menu> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);

        return new CartItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartItemHolder holder, int position) {
        Menu item = cartList.get(position);
        holder.id.setText(item.id);
        holder.cartMenu.setText(item.meal);
        holder.totprice.setText(item.concatedPrice);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void addMenu(Menu menu, int index){
        cartList.add(menu);
        notifyItemInserted(index);
    }

    public List<Menu> getCartList(){
        return cartList;
    }

    public boolean searchExistingItem(Menu m, CartAdapter ca){
        for(Menu menu : ca.getCartList()){
            if(menu.meal == m.meal){
                return true;
            }
        }
        return false;
    }

    public int countTotalPrice(CartAdapter ca){
        int total = 0;
        for(Menu menu : ca.getCartList()){
            total = total + menu.price;
        }
        return total;
    }
}
