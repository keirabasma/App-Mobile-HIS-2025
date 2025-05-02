package com.example.tp3_convertiseur_akram_khichane;// Remplace par ton vrai package

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private EditText valeurEditText;
    private RadioButton radioDinarEuro, radioEuroDinar;
    private TextView resultatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des composants XML avec le code Java
        valeurEditText = findViewById(R.id.valeur);
        radioDinarEuro = findViewById(R.id.radioDinarEuro);
        radioEuroDinar = findViewById(R.id.radioEuroDinar);
        resultatTextView = findViewById(R.id.resultat);

        Button btnConvertir = findViewById(R.id.btnConvertir);
        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }

    // Méthode de conversion appelée par le bouton
    private void convertir() {
        String valeurStr = valeurEditText.getText().toString().trim();

        // Vérification si le champ est vide
        if (valeurStr.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        float valeur = Float.parseFloat(valeurStr);
        float resultat;

        // Conversion selon le bouton radio sélectionné
        if (radioDinarEuro.isChecked()) {
            resultat = dinarToEuro(valeur);
            resultatTextView.setText(String.format("%.2f DZD = %.2f EUR", valeur, resultat));
        } else if (radioEuroDinar.isChecked()) {
            resultat = euroToDinar(valeur);
            resultatTextView.setText(String.format("%.2f EUR = %.2f DZD", valeur, resultat));
        } else {
            Toast.makeText(this, "Sélectionnez un sens de conversion", Toast.LENGTH_SHORT).show();
        }
    }

    // Conversion Dinar vers Euro
    private float dinarToEuro(float valeurDinar) {
        return (float) (valeurDinar * 0.0071); // 1 DZD = 0.0071 EUR
    }

    // Conversion Euro vers Dinar
    private float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 140.45); // 1 EUR = 140.45 DZD
    }

    // Création du menu d’options (barre du haut)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Conversion Dinar <-> Dollar");
        menu.add(0, 2, 1, "Quitter");
        return true;
    }

    // Gestion des clics sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            Toast.makeText(this, "Conversion Dinar <-> Dollar non implémentée", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == 2) {
            finish(); // Quitte l’application
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}