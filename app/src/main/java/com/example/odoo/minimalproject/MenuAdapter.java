package com.example.odoo.minimalproject;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuItemHolder> {
    private List<Menu> menuList;

    public MenuAdapter(ArrayList<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MenuItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item_layout, parent, false);

        return new MenuItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuItemHolder holder, int position) {
        Menu item = menuList.get(position);
        holder.foodName.setText(item.getMeal());
        holder.price.setText(String.valueOf(item.getPrice()));
//        holder.imgSrc.setImageResource(c.getResources().getIdentifier(item.getImgSrc(), "drawable", "com.example.odoo.minimalproject"));

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //dari kiri
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wrapper));
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, null);
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
            }

            @Override
            public void onOpen(SwipeLayout layout) {
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
            }

            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }
}