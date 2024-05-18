package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class LernkarteHinzufuegen {
    public void lernkarteHinzufuegen(String frage, String antwort, String kategorie, Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.addLernkarte(db, frage, antwort, kategorie);
        db.close();
    }
}

