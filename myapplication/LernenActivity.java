package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class LernenActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    private Button buttonOption1;
  //  private Button buttonOption2; fuer weitere Lernoptionen
  //  private Button buttonOption3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_options);

        // Hier erstellst du eine Instanz von DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Hier rufst du die Methode getZufälligeAntworten auf
        int frageID = getQuestionID(); // Annahme: getQuestionID() ist eine Methode, die die Frage-ID zurückgibt
        List<String> lernkarte = dbHelper.getZufälligeAntworten(frageID);

        // Buttons und ihre OnClickListener initialisieren
        buttonOption1 = findViewById(R.id.buttonOption1);
        //buttonOption2 = findViewById(R.id.buttonOption2);
        //buttonOption3 = findViewById(R.id.buttonOption3);

        buttonOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier die Logik für Option 1 implementieren
                // Beispiel: Anzeigen der zufälligen Lernkarte in einem Toast
                String lernkarteText = "Frage: " + lernkarte.get(0) + "\n" +
                        "Antwort 1: " + lernkarte.get(1) + "\n" +
                        "Antwort 2: " + lernkarte.get(2) + "\n" +
                        "Antwort 3: " + lernkarte.get(3);
                Toast.makeText(LernenActivity.this, lernkarteText, Toast.LENGTH_SHORT).show();
            }
        });

        /*buttonOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier die Logik für Option 2 implementieren
            }
        });

        buttonOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier die Logik für Option 3 implementieren
            }
        });*/
    }
    public void backToNavigation(View view) {
        finish();
    }
    private int getQuestionID() {
        // Hier implementierst du die Logik, um die Frage-ID zu erhalten
        return 123; // Beispiel: Rückgabe einer festen Frage-ID (123)
    }
}