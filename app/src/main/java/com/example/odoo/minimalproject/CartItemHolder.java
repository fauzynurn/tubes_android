package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by odoo on 4/29/18.
 */

public class CartItemHolder extends RecyclerView.ViewHolder {
    public TextView cartMenu,totprice,id;

    public CartItemHolder(View view){
        super(view);
        id = view.findViewById(R.id.id_menu);
        cartMenu = view.findViewById(R.id.the_menu);
        totprice = view.findViewById(R.id.total_price_per_item);
    }
}
