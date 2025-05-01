package com.example.myapplication;


import android.os.Bundle;
import androidx.activity.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.*;
import android.widget.*;


public class Activity2 extends AppCompatActivity {

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        resultText = findViewById(R.id.resultText);

        // Récupérer le résultat depuis l'Intent
        String resultat = getIntent().getStringExtra("resultat");
        resultText.setText("Résultat : " + resultat);

        //retour
     Button btnRetour = findViewById(R.id.btnRetour);
     btnRetour.setOnClickListener(v ->  {

            finish();
        });
    }
}