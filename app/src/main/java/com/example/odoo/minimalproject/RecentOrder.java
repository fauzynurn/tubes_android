package com.example.odoo.minimalproject;

import java.util.Date;

/**
 * Created by odoo on 4/8/18.
 */

public class RecentOrder {
    private String orderDate;
    private String foodOrders;
    private boolean showShimmer;


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(String foodOrders) {
        this.foodOrders = foodOrders;
    }

    public boolean isShowShimmer() {
        return showShimmer;
    }

    public void setShowShimmer(boolean showShimmer) {
        this.showShimmer = showShimmer;
    }
}
