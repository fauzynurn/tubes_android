package com.example.odoo.minimalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by odoo on 4/28/18.
 */

public class BottomSheetDialog extends Dialog {
    RecyclerView cartList;
    TextView totalPrice;
    CartAdapter cartAdapter;
    FancyButton checkOutBtn;
    Context menuActivityContext;
    FragmentManager fManager;
    AddOrder addOrder;

    public BottomSheetDialog(Context c,LayoutInflater li, CartAdapter ca, FragmentManager fm){
        super(c,R.style.MaterialDialogSheet);
        menuActivityContext = c;
        cartAdapter = ca;
        fManager = fm;
        if(ca.getCartList().isEmpty()){
            this.setContentView(li.inflate(R.layout.cart_empty_layout,null));
        }else{
            View view = li.inflate(R.layout.cart_layout, null);
            this.setContentView(view);
            checkOutBtn = view.findViewById(R.id.checkout_btn);
            cartList = view.findViewById(R.id.cart_list);
            totalPrice = view.findViewById(R.id.total_price);
            totalPrice.setText(String.valueOf(ca.countTotalPrice(ca)) + "k");
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            cartList.setLayoutManager(llm);
            cartList.setAdapter(ca);

            checkOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addOrder = new AddOrder(getContext(), cartAdapter.getCartList(),cartAdapter.countTotalPrice(cartAdapter),fManager,menuActivityContext);
                    menuActivityContext.startActivity(new Intent(menuActivityContext,HomeActivity.class));
                    ((Activity)menuActivityContext).finish();
                }
            });
        }
        this.setCancelable(true);
        this.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.show();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view;
//        if(ca.getCartList().isEmpty()){
//            view = inflater.inflate(R.layout.cart_empty_layout, container, false);
//        }else {
//            view = inflater.inflate(R.layout.cart_layout, null);
//            checkOutBtn = view.findViewById(R.id.checkout_btn);
//            cartList = view.findViewById(R.id.cart_list);
//            totalPrice = view.findViewById(R.id.total_price);
//            totalPrice.setText(String.valueOf(ca.countTotalPrice(ca)) + "k");
//            LinearLayoutManager llm = new LinearLayoutManager(getContext());
//            cartList.setLayoutManager(llm);
//            cartList.setAdapter(ca);
//
//            checkOutBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    addOrder = new AddOrder(getContext(), ca.getCartList(),ca.countTotalPrice(ca),fm,menuActivityContext);
//                    startActivity(new Intent(menuActivityContext,HomeActivity.class));
//                    getActivity().finish();
//                }
//            });
//
//        }
//        return view;
//    }

}
