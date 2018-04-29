package com.example.odoo.minimalproject;

/**
 * Created by odoo on 4/25/18.
 */

public class ObservableInteger {
    private OnIntegerChangeListener listener;
    private int value;

    public void setOnIntegerChangeListener (OnIntegerChangeListener l){
        listener = l;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
        if(listener != null){
            listener.onIntegerChanged(value);
        }
    }
}
