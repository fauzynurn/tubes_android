package com.example.odoo.minimalproject;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Created by odoo on 4/17/18.
 */

public class Menu{
    public String id = "";
    public String meal = "";
    public int price = 0;
    public String concatedPrice = "";
    public String imgSrc = "";
    public String jenis = "";

    public Menu(JSONObject json){
        super();
        try {
            if(json.has("idmenu")){
                this.id = json.getString("idmenu");
            }
            if(json.has("namamenu")) {
                this.meal = json.getString("namamenu");
            }
            if(json.has("hargaint")){
                this.price = json.getInt("hargaint");
            }
            if(json.has("hargastring")){
                this.concatedPrice = json.getString("hargastring");
            }
            if(json.has("src")) {
                this.imgSrc = json.getString("src");
            }
            if(json.has("jenis")) {
                this.jenis = json.getString("jenis");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Menu(){}

    public static Comparator<Menu> MenuByName = new Comparator<Menu>() {
        @Override
        public int compare(Menu menu1, Menu menu2) {
            return menu1.meal.compareTo(menu2.meal);
        }
    };
}
