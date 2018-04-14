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
import java.util.Calendar;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by odoo on 4/5/18.
 */

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecentOrderAdapter roAdapter;
    boolean isThemed;
    View parentView;
    boolean toggle_wishlist;
    Button order;
    BottomSheetDialog bsd;
    private DatabaseReference mRef;
    Button callMe;
    Button addOrder;
    RecentOrder rOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statusBarSetter statbarsetter = new statusBarSetter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_empty);
        getWindow().setStatusBarColor(Color.WHITE);
        isThemed = statbarsetter.setMiuiStatusBarIconDarkMode(HomeActivity.this, true);
        if (!isThemed) {
            getWindow().setStatusBarColor(Color.parseColor("#47D4AE"));
        }
//        toggle_wishlist = false;
//
//        parentView = getLayoutInflater().inflate(R.layout.wishlist_layout, null);
//        bsd = new BottomSheetDialog(HomeActivity.this);
//        bsd.setContentView(parentView);
//        callMe = findViewById(R.id.press_me);
//        callMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle_wishlist = true;
//            }
//        });
//        addOrder = findViewById(R.id.add_order);
//        addOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BottomSheetBehavior bsb = BottomSheetBehavior.from((View) parentView.getParent());
//                bsb.setPeekHeight(1300);
//                bsd.show();
//            }
//        });
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
