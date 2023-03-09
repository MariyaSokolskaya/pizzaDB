package com.example.pizzadb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PizzaDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "pizzaOrders.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "orders";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_DATA = "data";
    static final String COLUMN_MONEY = "money";
    static final String COLUMN_NAMES = "names";
    static final String COLUMN_CLIENT = "client";

    public PizzaDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String str = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_DATA + " text, " +
                COLUMN_MONEY + " real, " +
                COLUMN_NAMES + " text, " +
                COLUMN_CLIENT + " text);";
        sqLiteDatabase.execSQL(str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
