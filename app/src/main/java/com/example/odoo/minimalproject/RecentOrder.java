package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/8/18.
 */

public class RecentOrder {
    private String orderedDate;
    private String orderedMenu;
    private String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
