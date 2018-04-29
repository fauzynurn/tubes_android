package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/28/18.
 */

public class Drink extends Menu {
    private boolean coldDrink = true;


    public boolean isColdDrink() {
        return coldDrink;
    }

    public void setColdDrink(boolean coldDrink) {
        this.coldDrink = coldDrink;
    }
}
