package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lern_app.db";
    private static final int DATABASE_VERSION = 1;
    // Konstruktor
    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL-Befehle zum Erstellen der Tabellen ausführen
        db.execSQL("CREATE TABLE IF NOT EXISTS antworten (Antwort_ID INTEGER PRIMARY KEY AUTOINCREMENT, Antwort TEXT, Frage_ID INTEGER, FOREIGN KEY(Frage_ID) REFERENCES fragen(Frage_ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS fragen (Frage_ID INTEGER PRIMARY KEY AUTOINCREMENT, Frage TEXT, Antwort_ID INTEGER, Kategorie_ID INTEGER, Set_ID INTEGER, FOREIGN KEY(Antwort_ID) REFERENCES antworten(Antwort_ID), FOREIGN KEY(Kategorie_ID) REFERENCES kategorien(Kategorie_ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS kategorien (Kategorie_ID INTEGER PRIMARY KEY, Kategoriename TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS testergebnisse (ID INTEGER PRIMARY KEY AUTOINCREMENT, Benutzer_ID INTEGER, Ergebnis INTEGER, FOREIGN KEY(Benutzer_ID) REFERENCES benutzer(Benutzer_ID));");
        db.execSQL("CREATE TABLE IF NOT EXISTS benutzer (Benutzer_ID INTEGER PRIMARY KEY AUTOINCREMENT, Benutzername TEXT, Passwort TEXT);");
    }
    public void addTestergebnis(int benutzerID, int ergebnis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Benutzer_ID", benutzerID);
        values.put("Ergebnis", ergebnis);
        db.insert("testergebnisse", null, values);
        db.close();
    }
    public boolean prüfeAnmeldung(String benutzername, String passwort) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM benutzer WHERE Benutzername = ? AND Passwort = ?", new String[]{benutzername, passwort});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
    public boolean erstelleBenutzer(String benutzername, String passwort) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Benutzername", benutzername);
        values.put("Passwort", passwort);
        long result = db.insert("benutzer", null, values);
        return result != -1;
    }
    @SuppressLint("Range")
    private int getKategorieID(String kategorieName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Kategorie_ID FROM kategorien WHERE Kategoriename = ?", new String[]{kategorieName});
        int kategorieID = -1;
        if (cursor.moveToFirst()) {
            kategorieID = cursor.getInt(cursor.getColumnIndex("Kategorie_ID"));
        }
        cursor.close();
        return kategorieID;
    }
    public void addLernkarte(SQLiteDatabase db, String frage, String antwort, String kategorie) {
        ContentValues values = new ContentValues();
        values.put("Frage", frage);
        values.put("Antwort", antwort);
        values.put("Kategorie_ID", getKategorieID(kategorie)); // Kategorie_ID einfügen
        db.insert("fragen", null, values);
    }
    public void deleteLernkarte(SQLiteDatabase db, int lernkartenID) {
        db.delete("fragen", "Frage_ID=?", new String[]{String.valueOf(lernkartenID)});
        db.close();
    }
    @SuppressLint("Range")
    public List<String> getZufälligeAntworten(int frageID) {
        List<String> antworten = new ArrayList<>();
        // Zuerst die korrekte Antwort-ID für die Frage abrufen
        int korrekteAntwortID = getKorrekteAntwortID(frageID);
        // Dann die korrekte Antwort abrufen
        String korrekteAntwort = getAntwortByID(korrekteAntwortID);
        antworten.add(korrekteAntwort);
        // Dann zwei falsche Antworten zufällig auswählen
        Random random = new Random();
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT Antwort FROM antworten WHERE Frage_ID = ? AND Antwort_ID != ? ORDER BY RANDOM() LIMIT 2",
                new String[]{String.valueOf(frageID), String.valueOf(korrekteAntwortID)}
        );
        while (cursor.moveToNext()) {
            antworten.add(cursor.getString(cursor.getColumnIndex("Antwort")));
        }
        cursor.close();
        // Die Reihenfolge der Antworten mischen
        Collections.shuffle(antworten);
        return antworten;
    }
    @SuppressLint("Range")
    private String getAntwortByID(int antwortID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Antwort FROM antworten WHERE Antwort_ID = ?", new String[]{String.valueOf(antwortID)});
        String antwort = "";
        if (cursor.moveToFirst()) {
            antwort = cursor.getString(cursor.getColumnIndex("Antwort"));
        }
        cursor.close();
        return antwort;
    }
    @SuppressLint("Range")
    private int getKorrekteAntwortID(int frageID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Antwort_ID FROM fragen WHERE Frage_ID = ?", new String[]{String.valueOf(frageID)});
        int antwortID = -1;
        if (cursor.moveToFirst()) {
            antwortID = cursor.getInt(cursor.getColumnIndex("Antwort_ID"));
        }
        cursor.close();
        return antwortID;
    }
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
       // fals die datenbank geupdatet werden muss wenn z.B. tabellen hinzugefuegt werden muessen
    }
    public boolean hasQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM fragen", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
}
