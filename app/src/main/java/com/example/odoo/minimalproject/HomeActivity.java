package com.example.odoo.minimalproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/5/18.
 */

public class HomeActivity extends AppCompatActivity {
    private RecentOrderAdapter roAdapter;
    boolean isThemed;
    Button addOrder;
    DatabaseReference mRef;
    RecentOrder rOrder;
    ImageView watermelon;
    TextView nothingHere,tapIconText,recentOrderText;
    RecyclerView recentOrderList;
    List<RecentOrder> roList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarSetter statbarsetter = new statusBarSetter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        watermelon = findViewById(R.id.watermelon_icon);
        nothingHere = findViewById(R.id.nothing_here);
        tapIconText = findViewById(R.id.tap_icon_clue);
        recentOrderText = findViewById(R.id.recent_order_text);
        recentOrderList = findViewById(R.id.recent_order_list);
//        mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child("161511049").child("orderedMenu");
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.hasChildren()){
//                    watermelon.setVisibility(View.INVISIBLE);
//                    nothingHere.setVisibility(View.INVISIBLE);
//                    tapIconText.setVisibility(View.INVISIBLE);
//                    recentOrderText.setVisibility(View.VISIBLE);
//                    recentOrderList.setVisibility(View.VISIBLE);
//                    roList = new ArrayList<>();
//                    roAdapter = new RecentOrderAdapter(roList);
//                    for(DataSnapshot d : dataSnapshot.getChildren()){
//                        rOrder = d.getValue(RecentOrder.class);
//                        roList.add(0,rOrder);
//                    }
//                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
//                    recentOrderList.setLayoutManager(mLayoutManager);
//                    recentOrderList.setItemAnimator(new DefaultItemAnimator());
//                    recentOrderList.setAdapter(roAdapter);
//                }else{
//                    watermelon.setVisibility(View.VISIBLE);
//                    nothingHere.setVisibility(View.VISIBLE);
//                    tapIconText.setVisibility(View.VISIBLE);
//                    recentOrderText.setVisibility(View.INVISIBLE);
//                    recentOrderList.setVisibility(View.GONE);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
        addOrder = findViewById(R.id.add_order);
        addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BottomSheetDialog bsd = new BottomSheetDialog(HomeActivity.this);
//                View bsv = getLayoutInflater().inflate(R.layout.add_rice_layout,null);
//                bsd.setContentView(bsv);
//                bsd.show();
//                Intent i = new Intent(HomeActivity.this, FoodListActivity.class);
//                startActivity(i);
            }
        });
//        order = parentView.findViewById(R.id.post_order);
//        order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText foods = parentView.findViewById(R.id.food_input);
//                setOrder(foods.getText().toString());
//                bsd.hide();
//                Toast.makeText(getApplicationContext(),"Your order has been submitted. Thank you!",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child("161511049").child("orderedMenu");
//                mRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        final ArrayList<RecentOrder> listItem = new ArrayList<>();
//                        roAdapter = new RecentOrderAdapter(listItem);
//                        for(DataSnapshot d : dataSnapshot.getChildren()){
//                            rOrder = d.getValue(RecentOrder.class);
//                            listItem.add(0,rOrder);
//                        }
//                        roAdapter.notifyDataSetChanged();
//                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//                        recyclerView.setLayoutManager(mLayoutManager);
//                        recyclerView.setItemAnimator(new DefaultItemAnimator());
//                        recyclerView.setAdapter(roAdapter);
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//    }
//
//    public void setOrder(String menu){
//        OrderedItem oi = new OrderedItem(menu);
//        oi.setOrderedDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(Calendar.getInstance().getTime()));
//        mRef.push().setValue(oi);
//    }
//    String dateNow = new SimpleDateFormat("dd-MM-yy HH:mm").format(Calendar.getInstance().getTime());
    }
}
