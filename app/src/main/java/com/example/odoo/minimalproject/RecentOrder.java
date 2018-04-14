package com.example.odoo.minimalproject;

import java.util.Date;

/**
 * Created by odoo on 4/8/18.
 */

public class RecentOrder {
    private String orderedDate;
    private String orderedMenu;


    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getOrderedMenu() {
        return orderedMenu;
    }

    public void setOrderedMenu(String orderedMenu) {
        this.orderedMenu = orderedMenu;
    }
}
