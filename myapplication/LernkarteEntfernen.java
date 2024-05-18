package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LernkarteEntfernen {
    private Context context;

    public LernkarteEntfernen(Context context) {
        this.context = context;
    }

    public void lernkarteEntfernen(int lernkartenID) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.deleteLernkarte(db, lernkartenID);
        dbHelper.close();
    }
}