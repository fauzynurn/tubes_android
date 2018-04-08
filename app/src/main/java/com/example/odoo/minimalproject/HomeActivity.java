package com.example.odoo.minimalproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Collections;

/**
 * Created by odoo on 4/5/18.
 */

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecentOrderAdapter roAdapter;
    boolean isThemed;
    private DatabaseReference mRef;
    Button addOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarSetter statbarsetter = new statusBarSetter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        addOrder = findViewById(R.id.add_order);
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bsd = new BottomSheetDialog(HomeActivity.this);
                View parentView = getLayoutInflater().inflate(R.layout.add_order_layout, null);
                bsd.setContentView(parentView);
                BottomSheetBehavior bsb = BottomSheetBehavior.from((View) parentView.getParent());
                bsb.setPeekHeight(900);
                bsd.show();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child("161511049").child("orderedMenu");
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final ArrayList<RecentOrder> listItem = new ArrayList<>();
                        roAdapter = new RecentOrderAdapter(listItem);
                        for(DataSnapshot d : dataSnapshot.getChildren()){
                            RecentOrder rOrder = d.getValue(RecentOrder.class);
                            listItem.add(0,rOrder);
                        }
                        roAdapter.notifyDataSetChanged();
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(roAdapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
//    String dateNow = new SimpleDateFormat("dd-MM-yy HH:mm").format(Calendar.getInstance().getTime());
}
