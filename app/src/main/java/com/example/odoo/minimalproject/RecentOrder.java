package com.example.odoo.minimalproject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/8/18.
 */

public class RecentOrder {
    public String orderedDate;
    public int totalPrice;
    public String status;


    public RecentOrder(JSONObject json){
        super();
        try {
            this.orderedDate = json.getString("tglpesan");
            this.totalPrice = json.getInt("totalharga");
            this.status = json.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
