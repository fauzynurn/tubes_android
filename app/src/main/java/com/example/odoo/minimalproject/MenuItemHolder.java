package com.example.odoo.minimalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidviewhover.BlurLayout;

import java.util.List;

/**
 * Created by odoo on 5/16/18.
 */

public class MenuItemHolder extends RecyclerView.ViewHolder {
    public ImageView roundedImage;
    public TextView menuName;
    public TextView price;
    public TextView priceInt;
    public TextView id;
    CartAdapter cartAdapter;
    Context c;

    public MenuItemHolder(final Context c, View view, CartAdapter ca){
        super(view);
        cartAdapter = ca;
        this.c = c;
        priceInt = view.findViewById(R.id.intPrice);
        roundedImage = view.findViewById(R.id.rounded_image);
        menuName = view.findViewById(R.id.menu_text);
        price = view.findViewById(R.id.menu_price);
        id = view.findViewById(R.id.id_menu);
        roundedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Menu menu = new Menu();
                menu.id = id.getText().toString();
                menu.meal = menuName.getText().toString();
                menu.price = Integer.parseInt(priceInt.getText().toString());
                menu.concatedPrice = price.getText().toString();
                if (cartAdapter.searchExistingItem(menu,cartAdapter)) {
                    Toast.makeText(c, "You can only add the same menu once", Toast.LENGTH_SHORT).show();
                } else {
                    cartAdapter.addMenu(menu, cartAdapter.getItemCount());
                    Toast.makeText(c, menuName.getText().toString() + " added to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
