package com.example.odoo.minimalproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/8/18.
 */

public class RecentOrder {
    private String orderedDate;
    private List<String> orderedMenu = new ArrayList<String>();
    private String status;


    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public List<String> getOrderedMenu() {
        return orderedMenu;
    }

    public void setOrderedMenu(List<String> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
