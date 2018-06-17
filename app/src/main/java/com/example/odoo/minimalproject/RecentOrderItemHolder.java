package com.example.odoo.minimalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import info.hoang8f.widget.FButton;
import mehdi.sakout.fancybuttons.FancyButton;

public class RecentOrderItemHolder extends RecyclerView.ViewHolder {
    public static final String URL_GETDETAILORDER = "http://laniary-accountabil.000webhostapp.com/android/getDetailOrder.php";
    public TextView date, month, status, price, time,rawDate;
    public CardView cvLeft;
    public FButton seeDetail;
    Context context;

    public RecentOrderItemHolder(View view, Context c) {
        super(view);
        context = c;
        date = view.findViewById(R.id.date);
        month = view.findViewById(R.id.month);
        rawDate = view.findViewById(R.id.raw_date);
        time = view.findViewById(R.id.time);
        price = view.findViewById(R.id.price);

        cvLeft = view.findViewById(R.id.card_view1);
        seeDetail = view.findViewById(R.id.see_detail_btn);
        seeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String encodedDate = rawDate.getText().toString();
                Intent i = new Intent(context,DetailOrderActivity.class);
                i.putExtra("encodedDate",encodedDate);
                context.startActivity(i);
            }
        });
    }
//        public void setFragmentManager(FragmentManager fm){
//            this.fm = fm;
//        }
//        public void setCartAdapter(CartAdapter ca){
//            this.ca = ca;
//        }

}
