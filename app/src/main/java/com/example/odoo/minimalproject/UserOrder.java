package com.example.odoo.minimalproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserOrder {
    private String dateOrder;
    private String nama;
    private int totalPrice;
    private List<Menu> detailOrder = new ArrayList<>();

    public UserOrder(JSONObject json,List<Menu> detailOrder){
        super();
        try {
            this.dateOrder = json.getString("tglpesan");
            this.nama = json.getString("nama");
            this.totalPrice = json.getInt("totalharga");
            this.detailOrder = detailOrder;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public String getNama() {
        return nama;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<Menu> getDetailOrder() {
        return detailOrder;
    }
}
