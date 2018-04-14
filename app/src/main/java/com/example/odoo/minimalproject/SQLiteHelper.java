package com.example.odoo.minimalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class SQLiteHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "menus.db";
    private static final int DATABASE_VERSION = 1 ;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
}
