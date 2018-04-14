package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/11/18.
 */

public class OrderedItem {
    private String OrderedMenu;
    private String OrderedDate;

    public OrderedItem(String menu){
        OrderedMenu = menu;
    }

    public String getOrderedMenu() {
        return OrderedMenu;
    }

    public void setOrderedMenu(String orderedMenu) {
        OrderedMenu = orderedMenu;
    }

    public String getOrderedDate() {
        return OrderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        OrderedDate = orderedDate;
    }
}
