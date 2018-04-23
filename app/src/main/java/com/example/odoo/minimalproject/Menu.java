package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/17/18.
 */

public class Menu {
    private String meal;
    private int price;
    private String imgSrc;
    private boolean isRiceAllowed;
    private boolean withRice = true;


    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean isRiceAllowed() {
        return isRiceAllowed;
    }

    public void setRiceAllowed(boolean riceAllowed) {
        isRiceAllowed = riceAllowed;
    }

    public boolean isWithRice() {
        return withRice;
    }

    public void setWithRice(boolean withRice) {
        this.withRice = withRice;
    }
}
