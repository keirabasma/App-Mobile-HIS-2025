package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editFloat;
    RadioButton radioDinarToEuro, radioEuroToDinar;
    Button buttonConvertir;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFloat = findViewById(R.id.edit_float);
        radioDinarToEuro = findViewById(R.id.radioDinarToEuro);
        radioEuroToDinar = findViewById(R.id.radioEuroToDinar);
        buttonConvertir = findViewById(R.id.buttonConvertir);
        textResult = findViewById(R.id.textResult);

        buttonConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }
    private void convertir() {
        String texte = editFloat.getText().toString();

        if (texte.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        float valeur = Float.valueOf(texte);
        float resultat = 0;

        if (radioDinarToEuro.isChecked()) {
            resultat = dinarsToEuro(valeur);
        } else if (radioEuroToDinar.isChecked()) {
            resultat = euroToDinar(valeur);
        } else {
            Toast.makeText(this, "Veuillez choisir une option", Toast.LENGTH_SHORT).show();
            return;
        }

        textResult.setText("Résultat : " + resultat);
    }

    private float dinarsToEuro(float valeurDinar) {
        return valeurDinar * 0.004f;
    }

    private float euroToDinar(float valeurEuro) {
        return valeurEuro * 250.00f;
    }
}
