package com.example.odoo.minimalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/21/18.
 */

public class HomeFragment extends Fragment{
    RecyclerView recentOrderList;
    private RecentOrderAdapter roAdapter;
    RecentOrder rOrder;
    DatabaseReference mRef;

    public HomeFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTree = inflater.inflate(R.layout.page_1, container, false);
        recentOrderList = PageTree.findViewById(R.id.recent_order_list);
        mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child("161511049").child("orderedMenu");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<RecentOrder> listItem = new ArrayList<>();
                roAdapter = new RecentOrderAdapter(listItem);
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    rOrder = d.getValue(RecentOrder.class);
                    listItem.add(0,rOrder);
                }
                roAdapter.notifyDataSetChanged();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                recentOrderList.setLayoutManager(mLayoutManager);
                recentOrderList.setItemAnimator(new DefaultItemAnimator());
                recentOrderList.setAdapter(roAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return PageTree;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
