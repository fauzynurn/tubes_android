package com.example.odoo.minimalproject;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scalified.fab.ActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by odoo on 4/21/18.
 */

public class HomeActivity extends AppCompatActivity{
    boolean isThemed;
    ActionButton addFood;

    RecyclerView recentOrderList;
    private RecentOrderAdapter roAdapter;
//    ActionButton addOrder;
    private static final String TAG = "HomeActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        statusBarSetter statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        addFood = findViewById(R.id.add_menu);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(i);
            }
        });
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
//        addOrder = findViewById(R.id.add_order);
//        addOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(HomeActivity.this, MenuActivity.class);
//                startActivity(i);
//            }
//        });
        recentOrderList = findViewById(R.id.recent_order_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recentOrderList.setLayoutManager(llm);
        roAdapter = new RecentOrderAdapter(getDummyData());
        recentOrderList.setAdapter(roAdapter);
    }

    public List<RecentOrder> getDummyData(){
        List<RecentOrder> returnList = new ArrayList<>();
        RecentOrder ro1 = new RecentOrder();
        List<String> ro1List = new ArrayList<>();
        ro1List.add("Nasi Putih");
        ro1List.add("Ayam Bakar");
        ro1List.add("Mochafrio");
        ro1.setStatus("Paid");
        ro1.setOrderedDate("1/03/2017 10:40");
        ro1.setOrderedMenu(ro1List);

        RecentOrder ro2 = new RecentOrder();
        List<String> ro2List = new ArrayList<>();
        ro2List.add("Nasi Goreng");
        ro2List.add("Ayam Geprek");
        ro2List.add("Nutrisari");
        ro2.setStatus("Paid");
        ro2.setOrderedDate("26/02/2017 11:22");
        ro2.setOrderedMenu(ro2List);

        returnList.add(ro1); returnList.add(ro2);
        return returnList;
    }
    }
