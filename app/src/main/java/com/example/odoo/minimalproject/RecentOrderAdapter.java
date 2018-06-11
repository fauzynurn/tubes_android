package com.example.odoo.minimalproject;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class RecentOrderAdapter extends RecyclerView.Adapter<RecentOrderItemHolder> {

    private List<RecentOrder> RecentOrderList;
    String dayString;
    String timeDate;
    int date;

    public RecentOrderAdapter(List<RecentOrder> RecentOrderList) {
        this.RecentOrderList = RecentOrderList;
    }

    @Override
    public RecentOrderItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_order_item_layout, parent, false);
        return new RecentOrderItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentOrderItemHolder holder, int position) {
        RecentOrder item = RecentOrderList.get(position);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
        Date d = null;
        try {
            d = format.parse(item.orderedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dayString = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(d.getTime());
        timeDate = new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(d.getTime());
        date = d.getDate();
//        holder.mpAdapter.setDay(dayString);
        changeColorBasedOnDay(dayString,holder.cvLeft,holder.seeDetail);
//        holder.mpAdapter.setMenuList(item.getOrderedMenu());
        holder.date.setText(String.valueOf(date));
        holder.time.setText(timeDate);
        holder.month.setText(String.valueOf(new SimpleDateFormat("MMMM", Locale.ENGLISH).format(d.getTime())));
        holder.status.setText(item.status);
        String concatPrice = String.valueOf(item.totalPrice) + "k";
        holder.price.setText(concatPrice);
    }

    @Override
    public int getItemCount() {
        return RecentOrderList.size();
    }

    public void changeColorBasedOnDay(String day,CardView cardView, FButton btn){
        switch (day){
            case "Monday":
                cardView.setCardBackgroundColor(Color.parseColor("#24E5DC"));
                btn.setButtonColor(Color.parseColor("#24E5DC"));
                break;
            case "Tuesday":
                cardView.setCardBackgroundColor(Color.parseColor("#977DF1"));
                btn.setButtonColor(Color.parseColor("#977DF1"));
                break;
            case "Wednesday":
                cardView.setCardBackgroundColor(Color.parseColor("#3CA4A1"));
                btn.setButtonColor(Color.parseColor("#3CA4A1"));
                break;
            case "Thursday":
                cardView.setCardBackgroundColor(Color.parseColor("#E2658A"));
                btn.setButtonColor(Color.parseColor("#E2658A"));
                break;
            case "Friday":
                cardView.setCardBackgroundColor(Color.parseColor("#192A56"));
                btn.setButtonColor(Color.parseColor("#192A56"));
                break;
            case "Saturday":
                cardView.setCardBackgroundColor(Color.parseColor("#F0932B"));
                btn.setButtonColor(Color.parseColor("#F0932B"));
                break;
            case "Sunday":
                cardView.setCardBackgroundColor(Color.parseColor("#0097E6"));
                btn.setButtonColor(Color.parseColor("#0097E6"));
                break;
        }
    }
}