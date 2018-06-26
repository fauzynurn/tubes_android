package com.example.odoo.minimalproject;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

public class AddRiceDialog extends Dialog {
    FancyButton yes,no;
    Context context;
    CartAdapter ca;
    List<Menu> fList = new ArrayList<>();
    MenuItemHolder mItemHolder;

    public AddRiceDialog(List<Menu> foodList, Context c, LayoutInflater li, CartAdapter cartAdapter, final MenuItemHolder menuItemHolder){
        super(c,R.style.MaterialDialogSheet);
        ca = cartAdapter;
        fList = foodList;
        mItemHolder = menuItemHolder;
        context = c;
        this.setContentView(li.inflate(R.layout.add_rice_layout,null));
        yes = findViewById(R.id.yes_btn); no = findViewById(R.id.no_btn);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = new Menu();
                menu.jenis = "f";
                Menu menuNasi = findMenu("Nasi Putih");
                menu.meal = "Nasi " + mItemHolder.menuName.getText().toString();
                //modif price nya juga disini kalo harga udh fix
                menu.price = menuNasi.price + Integer.valueOf(mItemHolder.priceInt.getText().toString());
                menu.concatedPrice = String.valueOf(menu.price) + "k";
                ca.addMenu(menu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, menu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = findMenu(mItemHolder.menuName.getText().toString());
                ca.addMenu(menu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, menu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        this.setCancelable(true);
        this.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.show();
    }

    public Menu findMenu(String x){
        for(Menu menu : fList){
            if(menu.meal.equals(x)){
                return menu;
            }
        }
        return new Menu();
    }
}
