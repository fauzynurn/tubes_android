package com.example.odoo.minimalproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 5/14/18.
 */

public class MenuPreviewAdapter extends RecyclerView.Adapter<MenuPreviewItemHolder>{
    private List<String> menuList;
    String day;

    public MenuPreviewAdapter() {
    }

    public void setDay(String day){
        this.day = day;
    }

    @Override
    public MenuPreviewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_preview_item_layout, parent, false);
        return new MenuPreviewItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuPreviewItemHolder holder, int position) {
        String x = menuList.get(position);
        holder.menuPreview.setText(x);
        changeColorBasedOnDay(day,holder.ll);
    }

    public void setMenuList(List<String> menuList){
        this.menuList = menuList;
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void changeColorBasedOnDay(String day, LinearLayout ll){
        GradientDrawable gd = (GradientDrawable) ll.getBackground().getCurrent();
        switch (day){
            case "Monday":
                gd.setColor(Color.parseColor("#24E5DC"));
                break;
            case "Tuesday":
                gd.setColor(Color.parseColor("#977DF1"));
                break;
            case "Wednesday":
                gd.setColor(Color.parseColor("#3CA4A1"));
                break;
            case "Thursday":
                gd.setColor(Color.parseColor("#E2658A"));
                break;
            case "Friday":
                gd.setColor(Color.parseColor("#192A56"));
                break;
            case "Saturday":
                gd.setColor(Color.parseColor("#F0932B"));
                break;
            case "Sunday":
                gd.setColor(Color.parseColor("#0097E6"));
                break;
        }
    }
}
