package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KartenverwaltungActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Lernkarte> lernkartenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kartenverwaltung_activity);

        // RecyclerView initialisieren
        recyclerView = findViewById(R.id.recycler_view_lernkarten);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adapter initialisieren
        lernkartenList = getLernkartenFromDatabase(); // Hole die aktuellen Lernkarten aus der Datenbank
        adapter = new LernkartenAdapter(lernkartenList);

        recyclerView.setAdapter(adapter);

        // Button für das Hinzufügen von Lernkarten
        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier Logik für das Hinzufügen neuer Lernkarten implementieren
                // Zum Beispiel: Öffne eine Dialogansicht zum Eingeben einer neuen Lernkarte
            }
        });

        // Button für das Löschen von Lernkarten
        Button deleteButton = findViewById(R.id.button_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier Logik für das Löschen ausgewählter Lernkarten implementieren
                // Zum Beispiel: Markierte Lernkarten löschen
            }
        });
    }

    public void backToNavigation(View view) {
        finish(); // Diese Methode beendet die aktuelle Aktivität und kehrt zur vorherigen zurück
    }

    // Methode zum Abrufen der aktuellen Lernkarten aus der Datenbank
    private List<Lernkarte> getLernkartenFromDatabase() {
        // Hier die Logik zum Abrufen der Lernkarten aus der Datenbank implementieren
        // Zum Beispiel: Verwendung des DatabaseHelper, um Lernkarten aus der Datenbank abzurufen
        return null; // Dummy-Wert, hier solltest du die tatsächlichen Daten zurückgeben
    }
}