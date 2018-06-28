package com.example.odoo.minimalproject;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ColdOrHotDrinkDialog extends Dialog {
    Context c;
    TextView cold,hot;
    CartAdapter ca;
    public ColdOrHotDrinkDialog(final Context context, CartAdapter cartAdapter, final MenuItemHolder mItemHolder){
        super(context,R.style.MaterialDialogSheet);
        ca = cartAdapter;
        this.setCancelable(true);
        this.setContentView(R.layout.c_or_h_dialog);
        cold = findViewById(R.id.cold); hot = findViewById(R.id.hot);
        cold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = new Menu();
                menu.jenis = "d";
                menu.meal = mItemHolder.menuName.getText().toString() + " dingin";
                menu.price = Integer.valueOf(mItemHolder.priceInt.getText().toString());
                menu.concatedPrice = String.valueOf(menu.price) + "k";
                ca.addMenu(menu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, menu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = new Menu();
                menu.jenis = "d";
                menu.meal = mItemHolder.menuName.getText().toString() + " hangat";
                menu.price = Integer.valueOf(mItemHolder.priceInt.getText().toString());
                menu.concatedPrice = String.valueOf(menu.price) + "k";
                ca.addMenu(menu, ca.getItemCount());
                dismiss();
                Toast.makeText(context, menu.meal+" added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        this.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        this.getWindow().setGravity(Gravity.BOTTOM);
        this.show();
    }
}
