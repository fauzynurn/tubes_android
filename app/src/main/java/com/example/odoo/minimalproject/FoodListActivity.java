package com.example.odoo.minimalproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by odoo on 4/17/18.
 */

public class FoodListActivity extends AppCompatActivity{
    MenuAdapter mAdapter;
    RecyclerView recyclerView;
    DatabaseAccess dbAccess;
    statusBarSetter statbarsetter;
    boolean isThemed;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_list);
        statbarsetter = new statusBarSetter();
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(FoodListActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        recyclerView = findViewById(R.id.recycler_view);
        dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.open();
        mAdapter = new MenuAdapter(dbAccess.getMenusFromDatabase(),getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}
