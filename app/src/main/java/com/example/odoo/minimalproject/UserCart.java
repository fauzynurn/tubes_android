package com.example.odoo.minimalproject;

import java.util.ArrayList;

/**
 * Created by odoo on 4/25/18.
 */
 //This class should be instatiated only once per user
public class UserCart {
    private ArrayList<MenuForCart> userOrder = new ArrayList<>();

    public ArrayList<MenuForCart> getUserOrder() {
        return userOrder;
    }

    public int getMenuQty() {
        return this.userOrder.size();
    }

    public int countTotalPrice() {
        int totPrice = 0;
        for(MenuForCart mfc:userOrder){
            totPrice = totPrice + mfc.getTotalPriceMenu();
        }
        return totPrice;
    }
}
