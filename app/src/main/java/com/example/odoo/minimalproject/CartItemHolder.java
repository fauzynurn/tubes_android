package com.example.odoo.minimalproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by odoo on 4/29/18.
 */

public class CartItemHolder extends RecyclerView.ViewHolder {
    public TextView cartMenu,desc,totprice;

    public CartItemHolder(View view){
        super(view);
        cartMenu = view.findViewById(R.id.the_menu);
        desc = view.findViewById(R.id.description);
        totprice = view.findViewById(R.id.total_price_per_item);
    }
}
