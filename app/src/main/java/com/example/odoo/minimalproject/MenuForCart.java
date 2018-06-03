package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/29/18.
 */

public class MenuForCart extends Menu{
    private int qty;
    private int totalPriceMenu = 0;
    private String desc;

    public int getQty() {
        return qty;
    }

    public void setMeal(String meal){
        super.setMeal(meal);
//        this.getMeal() = this.getMeal() + " " + "(" + "x" + this.qty + ")";
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotalPriceMenu() {
        return totalPriceMenu;
    }

    public void setTotalPriceMenu(int totalPriceMenu) {
        this.totalPriceMenu = this.totalPriceMenu + qty*(super.getPrice());
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
