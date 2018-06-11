package com.example.odoo.minimalproject;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by odoo on 4/28/18.
 */

public class BottomSheetDialog extends BottomSheetDialogFragment {
    RecyclerView cartList;
    TextView totalPrice;
    CartAdapter ca;
    FancyButton checkOutBtn;
    FragmentManager fm;
    AddOrder addOrder;
    public BottomSheetDialog(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        if(ca.getCartList().isEmpty()){
            view = inflater.inflate(R.layout.cart_empty_layout, container, false);
        }else {
            view = inflater.inflate(R.layout.cart_layout, container, false);
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
                    try {
                        dismiss();
                        addOrder = new AddOrder(getContext(), ca.getCartList(),ca.countTotalPrice(ca),fm);
                        Intent i = new Intent(getContext(),HomeActivity.class);
                        startActivity(i);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        return view;
    }
    public void setFragmentManager(FragmentManager fm){
        this.fm = fm;
    }
    public void setCartAdapter(CartAdapter ca){
        this.ca = ca;
    }

}
