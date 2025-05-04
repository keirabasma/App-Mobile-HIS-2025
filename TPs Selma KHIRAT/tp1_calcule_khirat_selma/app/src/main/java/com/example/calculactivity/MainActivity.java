package com.example.calculactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText val1, val2;
    private RadioButton b1, b2;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // relie le layout à l'activité

        // Initialisation des composants de l'interface
        val1 = findViewById(R.id.val1);
        val2 = findViewById(R.id.val2);
        b1 = findViewById(R.id.somme);
        b2 = findViewById(R.id.diff);
        res = findViewById(R.id.res);
    }

    // Méthode appelée quand on clique sur le bouton "Calculer"
    public void calculer(View view) {
        try {
            int n1 = Integer.parseInt(val1.getText().toString());
            int n2 = Integer.parseInt(val2.getText().toString());
            int resultat;

            // Vérifie quel bouton radio est sélectionné
            if (b1.isChecked()) {
                resultat = n1 + n2; // Somme
            } else {
                resultat = n1 - n2; // Différence
            }

            // Affiche le résultat
            res.setText("Résultat : " + resultat);

        } catch (NumberFormatException e) {
            // En cas de champ vide ou saisie non numérique
            res.setText("Veuillez entrer des nombres valides !");
        }
    }
}
