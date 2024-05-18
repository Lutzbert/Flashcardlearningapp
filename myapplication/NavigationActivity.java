package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // Buttons aus der XML-Layoutdatei referenzieren und OnClickListener setzen
        Button buttonToLernen = findViewById(R.id.buttonToLernen);
        Button buttonToLernActivity = findViewById(R.id.buttonToLernActivity);
        Button buttonToScore = findViewById(R.id.buttonToScore);
        Button buttonToExit = findViewById(R.id.buttonToExit);

        // OnClickListener für jeden Button hinzufügen
        buttonToLernen.setOnClickListener(this);
        buttonToLernActivity.setOnClickListener(this);
        buttonToScore.setOnClickListener(this);
        buttonToExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Überprüfe, welcher Button geklickt wurde, und führe die entsprechende Aktion aus
        if (v.getId() == R.id.buttonToLernen) {
            startActivity(new Intent(this, LernenActivity.class));
        } else if (v.getId() == R.id.buttonToLernActivity) {
            startActivity(new Intent(this, KartenverwaltungActivity.class));
        } else if (v.getId() == R.id.buttonToScore) {
            startActivity(new Intent(this, Score.class));
        } else if (v.getId() == R.id.buttonToExit) {
            finish(); // Beende die Aktivität und somit die App
        }
    }
}