package com.example.odoo.minimalproject;

public class UserPreference {
    boolean isLoggedIn=false;
    String shortName;

    public String generateShortName(String rawName){
        String[] arrOfString = rawName.split("\\s+");
        String firstChar = arrOfString[0].substring(0,1);
        String secondChar = arrOfString[1].substring(0,1);
        String result = firstChar + secondChar;
        return result;
    }

    public void setShortName(String shortName){
        this.shortName = shortName;
    }

    public void changeLoginState(boolean b){
        isLoggedIn = b;
    }

}
