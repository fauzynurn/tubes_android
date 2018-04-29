package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

public class MenuItemHolder extends RecyclerView.ViewHolder {
    public SwipeLayout swipeLayout;
    public TextView foodName,price;
//    public ImageView imgSrc;
    public ImageView addToWish,addToFav;

    public MenuItemHolder(View view) {
        super(view);
        foodName = view.findViewById(R.id.menu_name);
        price = view.findViewById(R.id.price);
//        imgSrc = view.findViewById(R.id.bg_food);
        swipeLayout = view.findViewById(R.id.swipe);
        addToWish = view.findViewById(R.id.add_to_cart);
        addToFav = view.findViewById(R.id.add_to_fav);
    }
}
