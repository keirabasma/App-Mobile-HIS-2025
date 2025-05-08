package com.example.moyenne;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class echecActivity extends AppCompatActivity {

    TextView textMoyenneRecale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echec);

        textMoyenneRecale = findViewById(R.id.textMoyenneRecale);

        int moyenne = getIntent().getIntExtra("moyenne", 0);
        textMoyenneRecale.setText("Votre moyenne est : " + moyenne);
    }
}
