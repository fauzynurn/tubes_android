package com.example.odoo.minimalproject;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by odoo on 5/16/18.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuItemHolder> {
    private List<Menu> menuList;
    private Context c;
    CartAdapter ca;
    Menu item;
    ViewGroup parent;

    public MenuAdapter(Context c, List<Menu> menuList, CartAdapter myCart) {
        this.menuList = menuList;
        this.c = c;
        ca = myCart;
    }

    @Override
    public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_detail_layout, parent, false);
        this.parent = parent;
        return new MenuItemHolder(c, itemView, ca);
    }

    @Override
    public void onBindViewHolder(MenuItemHolder holder, int position) {
        item = menuList.get(position);
        int drawableResourceId = c.getResources().getIdentifier(item.imgSrc, "drawable", c.getPackageName());
        holder.id.setText(item.id);
        holder.roundedImage.setImageResource(drawableResourceId);
        holder.menuName.setText(item.meal);
        setMenuOnClickListener(holder);
        holder.priceInt.setText(String.valueOf(item.price));
        holder.price.setText(item.concatedPrice);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void setMenuOnClickListener(final MenuItemHolder mItemHolder) {
        if ((mItemHolder.menuName.getText().toString()).equals("Otok Owok")
                || (mItemHolder.menuName.getText().toString()).equals("Nasi Goreng")
                || (mItemHolder.menuName.getText().toString()).equals("Nasi Bakar")) {
            mItemHolder.roundedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CombinationDialog cd = new CombinationDialog(menuList,c, LayoutInflater.from(parent.getContext()), ca, mItemHolder);
                }
            });
        } else if (!(mItemHolder.menuName.getText().toString()).equals("Nasi Putih") &&
                (Integer.valueOf(mItemHolder.id.getText().toString())) < 13) {
            mItemHolder.roundedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddRiceDialog ar = new AddRiceDialog(menuList, c, LayoutInflater.from(parent.getContext()), ca, mItemHolder);
                }
            });
        } else if((mItemHolder.menuName.getText().toString()).equals("Teh Manis")) {
            mItemHolder.roundedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColdOrHotDrinkDialog cOrHot = new ColdOrHotDrinkDialog(c,ca,mItemHolder);
                }
            });
        }else{
            mItemHolder.roundedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Menu menu = findMenu(mItemHolder.menuName.getText().toString());
                    ca.addMenu(menu, ca.getItemCount());
                    Toast.makeText(c, menu.meal+" added to cart", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public Menu findMenu(String x){
        for(Menu menu : menuList){
            if(menu.meal.equals(x)){
                return menu;
            }
        }
        return new Menu();
    }
}
