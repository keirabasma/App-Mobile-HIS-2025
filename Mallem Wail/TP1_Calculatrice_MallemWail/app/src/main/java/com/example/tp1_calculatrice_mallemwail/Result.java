package com.example.tp1_calculatrice_mallemwail;
import android.os.Bundle;

import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;


public class Result extends AppCompatActivity {

    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        resultText = findViewById(R.id.resultText);

        // Récupérer le résultat depuis l'Intent
        String result = getIntent().getStringExtra("result");
        resultText.setText("Result is : " + result);

        //retour
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v ->  {

            finish();
        });
    }
}
