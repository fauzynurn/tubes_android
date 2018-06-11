package com.example.odoo.minimalproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by odoo on 4/17/18.
 */

public class Menu {
    public String id;
    public String meal;
    public int price;
    public String concatedPrice;
    public String imgSrc;
    public String jenis;

    public Menu(JSONObject json){
        super();
        try {
            this.id = json.getString("idmenu");
            this.meal = json.getString("namamenu");
            this.price = json.getInt("hargaint");
            this.concatedPrice = json.getString("hargastring");
            this.imgSrc = json.getString("src");
            this.jenis = json.getString("jenis");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Menu(){

    }
}
