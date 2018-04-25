package com.example.odoo.minimalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by odoo on 4/21/18.
 */

public class MenuFragment extends Fragment{
    RecyclerView menuRecycler;
    private MenuAdapter menuAdapter;
    Menu rOrder;
    public MenuFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTree = inflater.inflate(R.layout.page_2, container, false);
        menuRecycler = PageTree.findViewById(R.id.menu_list);
        ArrayList<Menu> menuItem = new ArrayList<>();
        menuItem = fillDataDummy();
        menuAdapter = new MenuAdapter(menuItem);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        menuRecycler.setLayoutManager(mLayoutManager);
        menuRecycler.setItemAnimator(new DefaultItemAnimator());
        menuRecycler.setAdapter(menuAdapter);
//        mRef = FirebaseDatabase.getInstance().getReference().getRoot().child("users").child("161511049").child("orderedMenu");
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                final ArrayList<RecentOrder> listItem = new ArrayList<>();
//                roAdapter = new RecentOrderAdapter(listItem);
//                for(DataSnapshot d : dataSnapshot.getChildren()){
//                    rOrder = d.getValue(RecentOrder.class);
//                    listItem.add(0,rOrder);
//                }
//                roAdapter.notifyDataSetChanged();
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//                recentOrderList.setLayoutManager(mLayoutManager);
//                recentOrderList.setItemAnimator(new DefaultItemAnimator());
//                recentOrderList.setAdapter(roAdapter);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
        return PageTree;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ArrayList<Menu> fillDataDummy(){
        ArrayList<Menu> arrMenu = new ArrayList<>();
        Menu m1 = new Menu();
        m1.setMeal("Ayam bakar madu");
        m1.setPrice(11000);

        Menu m2 = new Menu();
        m2.setMeal("Ayam bakar jeletot");
        m2.setPrice(11000);

        Menu m3 = new Menu();
        m3.setMeal("Soto ayam");
        m3.setPrice(10000);

        Menu m4 = new Menu();
        m4.setMeal("Otok owok ayam goreng");
        m4.setPrice(12000);

        Menu m5 = new Menu();
        m5.setMeal("Chicken katsu");
        m5.setPrice(11000);

        Menu m6 = new Menu();
        m6.setMeal("Mochafrio");
        m6.setPrice(3000);

        Menu m7 = new Menu();
        m7.setMeal("Lasagna bakar");
        m7.setPrice(16500);

        Menu m8 = new Menu();
        m8.setMeal("Nasi bakar ayam bakar");
        m8.setPrice(10000);

        arrMenu.add(m1);
        arrMenu.add(m2);
        arrMenu.add(m3);
        arrMenu.add(m4);
        arrMenu.add(m5);
        arrMenu.add(m6);
        arrMenu.add(m7);
        arrMenu.add(m8);

        return arrMenu;

    }
}
