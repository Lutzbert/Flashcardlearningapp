package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Setze die Zeitverzögerung für die Anzeige des Logos in Millisekunden
    private static final int SPLASH_DELAY = 2000; // Zum Beispiel 2000 Millisekunden (2 Sekunden)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Zeige das Logo für eine Verzögerung an, bevor die NavigationActivity gestartet wird
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Starte die NavigationActivity
                Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(intent);
                finish(); // Beende die MainActivity
            }
        }, SPLASH_DELAY);
    }
}