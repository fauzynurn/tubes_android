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

import java.util.List;

public class CombinationDialog extends Dialog {
    ImageView img1,img2,img3;
    TextView txt1,txt2,txt3,orderThisMenu;
    List<Menu> fList;
    Context context;
    CartAdapter ca;
    MenuItemHolder mItemHolder;

    public CombinationDialog(List<Menu> foodList, Context c, LayoutInflater li, CartAdapter cartAdapter, final MenuItemHolder menuItemHolder){
        super(c,R.style.MaterialDialogSheet);
        ca = cartAdapter;
        fList = foodList;
        mItemHolder = menuItemHolder;
        context = c;
        this.setContentView(li.inflate(R.layout.combination_menu_layout,null));
        img1 = findViewById(R.id.menu_combi1); img2 = findViewById(R.id.menu_combi2); img3 = findViewById(R.id.menu_combi3);
        txt1 = findViewById(R.id.text1); txt2 = findViewById(R.id.text2); txt3 = findViewById(R.id.text3);
        orderThisMenu = findViewById(R.id.order_this_menu);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu1 = findMenu(mItemHolder.menuName.getText().toString());
                Menu menu2 = findMenu(txt1.getText().toString());
                Menu combinedMenu = new Menu();
                combinedMenu.jenis = "f";
                combinedMenu.meal = menu1.meal + " " + menu2.meal;
                //modif price nya juga disini kalo harga udh fix
                combinedMenu.price = menu1.price + menu2.price;
                combinedMenu.concatedPrice = String.valueOf(combinedMenu.price) + "k";
                ca.addMenu(combinedMenu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, combinedMenu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu1 = findMenu(mItemHolder.menuName.getText().toString());
                Menu menu2 = findMenu(txt2.getText().toString());
                Menu combinedMenu = new Menu();
                combinedMenu.jenis = "f";
                combinedMenu.meal = menu1.meal + " " + menu2.meal;
                //modif price nya juga disini kalo harga udh fix
                combinedMenu.price = menu1.price + menu2.price;
                combinedMenu.concatedPrice = String.valueOf(combinedMenu.price) + "k";
                ca.addMenu(combinedMenu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, combinedMenu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu1 = findMenu(mItemHolder.menuName.getText().toString());
                Menu menu2 = findMenu(txt3.getText().toString());
                Menu combinedMenu = new Menu();
                combinedMenu.jenis = "f";
                combinedMenu.meal = menu1.meal + " " + menu2.meal;
                //modif price nya juga disini kalo harga udh fix
                combinedMenu.price = menu1.price + menu2.price;
                combinedMenu.concatedPrice = String.valueOf(combinedMenu.price) + "k";
                ca.addMenu(combinedMenu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, combinedMenu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        orderThisMenu.setOnClickListener(new View.OnClickListener() {
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
