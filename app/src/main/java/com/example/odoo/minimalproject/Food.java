package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/28/18.
 */

public class Food extends Menu {
    private boolean withRice = true;
    private boolean withChicken = false;
    private boolean riceAllowed;
    private boolean chickenAllowed;

    public boolean isWithRice() {
        return withRice;
    }

    public void setWithRice(boolean withRice) {
        this.withRice = withRice;
    }

    public boolean isWithChicken() {
        return withChicken;
    }

    public void setWithChicken(boolean withChicken) {
        this.withChicken = withChicken;
    }

    public boolean isRiceAllowed() {
        return riceAllowed;
    }

    public void setRiceAllowed(boolean riceAllowed) {
        this.riceAllowed = riceAllowed;
    }

    public boolean isChickenAllowed() {
        return chickenAllowed;
    }

    public void setChickenAllowed(boolean chickenAllowed) {
        this.chickenAllowed = chickenAllowed;
    }
}
