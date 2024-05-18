package com.example.myapplication;


import java.util.Arrays;
import java.util.List;

public class LernkartenManager {
    private List<Lernkarte> lernkarten;
    private int[] kategorien;

    public LernkartenManager(List<Lernkarte> lernkarten) {
        this.lernkarten = lernkarten;
        // Annahme: Wir haben 3 Kategorien: 0 - Schlecht, 1 - Mittel, 2 - Gut
        this.kategorien = new int[lernkarten.size()];
        Arrays.fill(kategorien, 0); // Alle Karten beginnen in der Kategorie "Schlecht"
    }

    public Lernkarte getNextLernkarte() {
        for (int i = 0; i < lernkarten.size(); i++) {
            if (kategorien[i] < 2) { // Nur Karten mit einer Kategorie < 2 (Gut) werden zurückgegeben
                return lernkarten.get(i);
            }
        }
        return null; // Falls alle Karten in der Kategorie "Gut" sind
    }

    public void updateKategorie(Lernkarte lernkarte, boolean richtigBeantwortet) {
        int index = lernkarten.indexOf(lernkarte);
        if (index != -1) {
            if (richtigBeantwortet) {
                if (kategorien[index] < 2) {
                    kategorien[index]++;
                }
            } else {
                kategorien[index] = 0; // Bei falscher Antwort zurück zur Kategorie "Schlecht"
            }
        }
    }
}