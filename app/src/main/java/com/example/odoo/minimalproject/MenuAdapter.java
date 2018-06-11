package com.example.odoo.minimalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public MenuAdapter(Context c, List<Menu> menuList, CartAdapter myCart) {
            this.menuList = menuList;
            this.c = c;
            ca = myCart;
        }

        @Override
        public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_detail_layout, parent, false);
            return new MenuItemHolder(c,itemView,ca);
        }

        @Override
        public void onBindViewHolder(MenuItemHolder holder, int position) {
            item = menuList.get(position);
            int drawableResourceId = c.getResources().getIdentifier(item.imgSrc, "drawable", c.getPackageName());
            holder.id.setText(item.id);
            holder.roundedImage.setImageResource(drawableResourceId);
            holder.menuName.setText(item.meal);
            holder.priceInt.setText(String.valueOf(item.price));
            holder.price.setText(item.concatedPrice);
        }


        @Override
        public int getItemCount() {
            return menuList.size();
        }

        public void updateList(List<Menu> mList){
            menuList = mList;
            notifyDataSetChanged();
        }

        public List<Menu> getMenuList(){
            return menuList;
        }
    }
