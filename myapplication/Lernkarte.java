package com.example.myapplication;

public class Lernkarte {
    private String frage;
    private String antwort;
    private String kategorie;
    private int wiederholfrequenz;

    public Lernkarte(String frage, String antwort, String kategorie) {
        this.frage = frage;
        this.antwort = antwort;
        this.kategorie = kategorie;
        this.wiederholfrequenz = 1; // Initialisiere die Wiederholfrequenz
    }

        // Getter und Setter für Frage und Antwort

    public String getFrage() {
        return frage;
    }

    public String getAntwort() {
        return antwort;
    }

            public Lernkarte(String frage, String antwort) {
                this.frage = frage;
                this.antwort = antwort;
            }



        public int getWiederholfrequenz() {
            return wiederholfrequenz;
        }

        public void erhöheWiederholfrequenz() {
            wiederholfrequenz++;
        }

        public void verringereWiederholfrequenz() {
            if (wiederholfrequenz > 1) {
                wiederholfrequenz--;
            }
    }
}
