package com.example.odoo.minimalproject;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class RecentOrderItemHolder extends RecyclerView.ViewHolder {

    public TextView date, month, status, price, time;
    public CardView cvLeft;
    public FButton seeDetail;
//    public MenuPreviewAdapter mpAdapter;
//    public RecyclerView previewList;


    public RecentOrderItemHolder(View view) {
        super(view);
        date = view.findViewById(R.id.date);
        month = view.findViewById(R.id.month);
        status = view.findViewById(R.id.status);
        time = view.findViewById(R.id.time);
        price = view.findViewById(R.id.price);
        cvLeft = view.findViewById(R.id.card_view1);
        seeDetail = view.findViewById(R.id.see_detail_btn);
//        previewList = view.findViewById(R.id.menu_preview_list);
//        previewList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
//        mpAdapter = new MenuPreviewAdapter();
//        previewList.setAdapter(mpAdapter);
    }
}
