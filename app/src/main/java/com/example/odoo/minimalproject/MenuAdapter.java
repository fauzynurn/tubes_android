package com.example.odoo.minimalproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by odoo on 5/16/18.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuItemHolder> {
        private List<Menu> menuList;

        public MenuAdapter(List<Menu> menuList) {
            this.menuList = menuList;
        }

        @Override
        public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_detail_layout, parent, false);
            return new MenuItemHolder(parent.getContext(),itemView);
        }

        @Override
        public void onBindViewHolder(MenuItemHolder holder, int position) {
            Menu item = menuList.get(position);

        }


        @Override
        public int getItemCount() {
            return menuList.size();
        }
    }
