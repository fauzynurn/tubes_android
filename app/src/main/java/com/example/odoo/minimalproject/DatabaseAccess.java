package com.example.odoo.minimalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by odoo on 4/10/18.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new SQLiteHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

//    public List<menus> getMenusFromDatabase(){
//        List<menus> menuList = new ArrayList();
//        c = db.rawQuery("SELECT menuname,price,menuimage FROM menu",null);
//        menus menu;
//        while(c.moveToNext()){
//            menu = new menus();
//            menu.setMenuName(c.getString(c.getColumnIndex("menuname")));
//            menu.setImgMenuName(c.getString(c.getColumnIndex("menuimage")));
//            menu.setPrice(c.getInt(c.getColumnIndex("price")));
//            menuList.add(menu);
//        }
//        return menuList;
//    }
}
