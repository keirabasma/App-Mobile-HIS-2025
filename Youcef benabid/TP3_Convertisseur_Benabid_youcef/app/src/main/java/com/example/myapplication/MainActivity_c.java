package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class MainActivity_convertisseur extends AppCompatActivity {

    private EditText editTextNumber;
    private RadioGroup radioGroup;
    private RadioButton radioDinarEuro, radioEuroDinar;
    private Button btnConvertir;
    private TextView textResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_convertisseur);

        editTextNumber = findViewById(R.id.editTextNumber);
        radioGroup = findViewById(R.id.radioGroup);
        radioDinarEuro = findViewById(R.id.radioDinarEuro);
        radioEuroDinar = findViewById(R.id.radioEuroDinar);
        btnConvertir = findViewById(R.id.btnConvertir);
        textResultat = findViewById(R.id.textResultat);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }

    private void convertir() {
        if (editTextNumber.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une valeur", Toast.LENGTH_SHORT).show();
            return;
        }

        float valeur = Float.parseFloat(editTextNumber.getText().toString());
        float resultat = 0;

        if (radioDinarEuro.isChecked()) {
            resultat = dinarsToEuro(valeur);
        } else if (radioEuroDinar.isChecked()) {
            resultat = euroToDinar(valeur);
        } else {
            Toast.makeText(this, "Veuillez sélectionner une conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        textResultat.setText(String.valueOf(resultat));
    }

    private float dinarsToEuro(float valeurDinar) {
        return (float) (valeurDinar / 140.45);
    }

    private float euroToDinar(float valeurEuro) {
        return (float) (valeurEuro * 140.45);
    }




}